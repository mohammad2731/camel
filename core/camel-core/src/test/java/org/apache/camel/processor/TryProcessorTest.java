/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.processor;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelException;
import org.apache.camel.ContextTestSupport;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for try .. handle routing (CAMEL-564).
 */
public class TryProcessorTest extends ContextTestSupport {

    private boolean handled;

    @Test
    public void testTryCatchFinallyProcessor() throws Exception {
        testTryCatchFinally("direct:processor");
    }

    @Test
    public void testTryCatchFinallyExpression() throws Exception {
        testTryCatchFinally("direct:expression");
    }

    @Test
    public void testTryCatchFinallyPredicate() throws Exception {
        testTryCatchFinally("direct:predicate");
    }

    private void testTryCatchFinally(String endpointName) throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMessageCount(0);

        getMockEndpoint("mock:last").expectedMessageCount(1);
        getMockEndpoint("mock:finally").expectedMessageCount(1);

        sendBody(endpointName, "<test>Hello World!</test>");
        assertTrue(handled, "Should have been handled");

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:processor").doTry().process(new ProcessorFail()).to("mock:result").doCatch(CamelException.class)
                        .process(new ProcessorHandle()).doFinally()
                        .to("mock:finally").end().to("mock:last");

                from("direct:expression").doTry().setBody(new ProcessorFail()).to("mock:result").doCatch(CamelException.class)
                        .process(new ProcessorHandle()).doFinally()
                        .to("mock:finally").end().to("mock:last");

                from("direct:predicate").doTry().to("direct:sub-predicate").doCatch(CamelException.class)
                        .process(new ProcessorHandle()).doFinally().to("mock:finally").end()
                        .to("mock:last");

                from("direct:sub-predicate").errorHandler(noErrorHandler()).filter(new ProcessorFail()).to("mock:result");
            }
        };
    }

    private static class ProcessorFail implements Processor, Predicate, Expression {
        @Override
        public void process(Exchange exchange) {
            throw new RuntimeCamelException(new CamelException("Force to fail"));
        }

        @Override
        public <T> T evaluate(Exchange exchange, Class<T> type) {
            throw new RuntimeCamelException(new CamelException("Force to fail"));
        }

        @Override
        public boolean matches(Exchange exchange) {
            throw new RuntimeCamelException(new CamelException("Force to fail"));
        }

        @Override
        public void init(CamelContext context) {
        }
    }

    private class ProcessorHandle implements Processor {
        @Override
        public void process(Exchange exchange) {
            handled = true;

            assertFalse(exchange.isFailed(), "Should not be marked as failed");

            Exception e = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
            assertNotNull(e, "There should be an exception");

            // If we handle CamelException it is what we should have as an
            // exception caught
            CamelException cause = assertIsInstanceOf(CamelException.class, e.getCause());
            assertNotNull(cause);
            assertEquals("Force to fail", cause.getMessage());
        }
    }

}
