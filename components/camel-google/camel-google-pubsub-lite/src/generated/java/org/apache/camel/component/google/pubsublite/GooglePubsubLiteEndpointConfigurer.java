/* Generated by camel build tools - do NOT edit this file! */
package org.apache.camel.component.google.pubsublite;

import javax.annotation.processing.Generated;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.ExtendedPropertyConfigurerGetter;
import org.apache.camel.spi.PropertyConfigurerGetter;
import org.apache.camel.spi.ConfigurerStrategy;
import org.apache.camel.spi.GeneratedPropertyConfigurer;
import org.apache.camel.util.CaseInsensitiveMap;
import org.apache.camel.support.component.PropertyConfigurerSupport;

/**
 * Generated by camel build tools - do NOT edit this file!
 */
@Generated("org.apache.camel.maven.packaging.EndpointSchemaGeneratorMojo")
@SuppressWarnings("unchecked")
public class GooglePubsubLiteEndpointConfigurer extends PropertyConfigurerSupport implements GeneratedPropertyConfigurer, PropertyConfigurerGetter {

    @Override
    public boolean configure(CamelContext camelContext, Object obj, String name, Object value, boolean ignoreCase) {
        GooglePubsubLiteEndpoint target = (GooglePubsubLiteEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "ackmode":
        case "ackMode": target.setAckMode(property(camelContext, org.apache.camel.component.google.pubsublite.GooglePubsubLiteConstants.AckMode.class, value)); return true;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": target.setBridgeErrorHandler(property(camelContext, boolean.class, value)); return true;
        case "concurrentconsumers":
        case "concurrentConsumers": target.setConcurrentConsumers(property(camelContext, java.lang.Integer.class, value)); return true;
        case "exceptionhandler":
        case "exceptionHandler": target.setExceptionHandler(property(camelContext, org.apache.camel.spi.ExceptionHandler.class, value)); return true;
        case "exchangepattern":
        case "exchangePattern": target.setExchangePattern(property(camelContext, org.apache.camel.ExchangePattern.class, value)); return true;
        case "lazystartproducer":
        case "lazyStartProducer": target.setLazyStartProducer(property(camelContext, boolean.class, value)); return true;
        case "loggerid":
        case "loggerId": target.setLoggerId(property(camelContext, java.lang.String.class, value)); return true;
        case "maxackextensionperiod":
        case "maxAckExtensionPeriod": target.setMaxAckExtensionPeriod(property(camelContext, int.class, value)); return true;
        case "maxmessagesperpoll":
        case "maxMessagesPerPoll": target.setMaxMessagesPerPoll(property(camelContext, java.lang.Integer.class, value)); return true;
        case "pubsubendpoint":
        case "pubsubEndpoint": target.setPubsubEndpoint(property(camelContext, java.lang.String.class, value)); return true;
        case "serializer": target.setSerializer(property(camelContext, org.apache.camel.component.google.pubsublite.serializer.GooglePubsubSerializer.class, value)); return true;
        case "serviceaccountkey":
        case "serviceAccountKey": target.setServiceAccountKey(property(camelContext, java.lang.String.class, value)); return true;
        default: return false;
        }
    }

    @Override
    public String[] getAutowiredNames() {
        return new String[]{"serializer"};
    }

    @Override
    public Class<?> getOptionType(String name, boolean ignoreCase) {
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "ackmode":
        case "ackMode": return org.apache.camel.component.google.pubsublite.GooglePubsubLiteConstants.AckMode.class;
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return boolean.class;
        case "concurrentconsumers":
        case "concurrentConsumers": return java.lang.Integer.class;
        case "exceptionhandler":
        case "exceptionHandler": return org.apache.camel.spi.ExceptionHandler.class;
        case "exchangepattern":
        case "exchangePattern": return org.apache.camel.ExchangePattern.class;
        case "lazystartproducer":
        case "lazyStartProducer": return boolean.class;
        case "loggerid":
        case "loggerId": return java.lang.String.class;
        case "maxackextensionperiod":
        case "maxAckExtensionPeriod": return int.class;
        case "maxmessagesperpoll":
        case "maxMessagesPerPoll": return java.lang.Integer.class;
        case "pubsubendpoint":
        case "pubsubEndpoint": return java.lang.String.class;
        case "serializer": return org.apache.camel.component.google.pubsublite.serializer.GooglePubsubSerializer.class;
        case "serviceaccountkey":
        case "serviceAccountKey": return java.lang.String.class;
        default: return null;
        }
    }

    @Override
    public Object getOptionValue(Object obj, String name, boolean ignoreCase) {
        GooglePubsubLiteEndpoint target = (GooglePubsubLiteEndpoint) obj;
        switch (ignoreCase ? name.toLowerCase() : name) {
        case "ackmode":
        case "ackMode": return target.getAckMode();
        case "bridgeerrorhandler":
        case "bridgeErrorHandler": return target.isBridgeErrorHandler();
        case "concurrentconsumers":
        case "concurrentConsumers": return target.getConcurrentConsumers();
        case "exceptionhandler":
        case "exceptionHandler": return target.getExceptionHandler();
        case "exchangepattern":
        case "exchangePattern": return target.getExchangePattern();
        case "lazystartproducer":
        case "lazyStartProducer": return target.isLazyStartProducer();
        case "loggerid":
        case "loggerId": return target.getLoggerId();
        case "maxackextensionperiod":
        case "maxAckExtensionPeriod": return target.getMaxAckExtensionPeriod();
        case "maxmessagesperpoll":
        case "maxMessagesPerPoll": return target.getMaxMessagesPerPoll();
        case "pubsubendpoint":
        case "pubsubEndpoint": return target.getPubsubEndpoint();
        case "serializer": return target.getSerializer();
        case "serviceaccountkey":
        case "serviceAccountKey": return target.getServiceAccountKey();
        default: return null;
        }
    }
}

