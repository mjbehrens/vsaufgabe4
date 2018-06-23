package de.tuberlin.cit.vs.communication;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class TestMain {

	public static void main(String[] args) {
		DefaultCamelContext ctxt = new DefaultCamelContext();
		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
		activeMQComponent.setTrustAllPackages(true);
        ctxt.addComponent("activemq", activeMQComponent);
        
        
	}
}
