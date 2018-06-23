package de.tuberlin.cit.vs.communication;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class A2 {

	public static void main(String[] args) {
		DefaultCamelContext ctxt = new DefaultCamelContext();
		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
		activeMQComponent.setTrustAllPackages(true);
        ctxt.addComponent("activemq", activeMQComponent);
        
        
		RouteBuilder route = new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("activemq:queue:testQueue").to("stream:out");
				
			}
			
		};
		
		ctxt.addComponent("activemq", activeMQComponent);
	}

}
