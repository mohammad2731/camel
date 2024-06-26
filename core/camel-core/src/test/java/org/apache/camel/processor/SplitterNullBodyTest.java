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

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;
import org.junit.jupiter.api.Test;

/**
 * Testing the splitter can work with null or empty bodies
 */
public class SplitterNullBodyTest extends ContextTestSupport {

    @Test
    public void testSplitABC() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(3);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        template.sendBody("direct:start", "A,B,C");

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSplitABCStreaming() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(3);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        template.sendBody("direct:streaming", "A,B,C");

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSplitEmptyList() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(0);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        List<?> list = new ArrayList<>();
        template.sendBody("direct:start", list);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSplitEmptyListStreaming() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(0);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        List<?> list = new ArrayList<>();
        template.sendBody("direct:streaming", list);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSplitNullBody() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(0);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        template.sendBody("direct:start", null);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSplitNullBodyStreaming() throws Exception {
        getMockEndpoint("mock:split").expectedMessageCount(0);
        getMockEndpoint("mock:result").expectedMessageCount(1);

        template.sendBody("direct:streaming", null);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").split(body()).to("mock:split").end().to("mock:result");

                from("direct:streaming").split(body()).streaming().to("mock:split").end().to("mock:result");
            }
        };
    }
}
