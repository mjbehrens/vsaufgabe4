package de.tuberlin.cit.vs.communication;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class A1 {

	public static void main(String[] args) throws JMSException {
		DefaultCamelContext ctxt = new DefaultCamelContext();
		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
		activeMQComponent.setTrustAllPackages(true);
        ctxt.addComponent("activemq", activeMQComponent);
		
		ActiveMQConnectionFactory conFactory = new ActiveMQConnectionFactory();
        conFactory.setTrustAllPackages(true);
        Connection con = conFactory.createConnection();
        
        Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("testQueue");
        
        RouteBuilder route = new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("stream:in").to("activemq:queue:testQueue");
				
			}
        	
        };
        
        ctxt.addComponent("activemq", activeMQComponent);
        
        
	}
}
