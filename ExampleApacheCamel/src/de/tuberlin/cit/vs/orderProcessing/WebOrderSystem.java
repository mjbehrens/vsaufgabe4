package de.tuberlin.cit.vs.orderProcessing;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class WebOrderSystem {
	
	private static Processor orderFactory = new Processor() {

		@Override
		public void process(Exchange exchange) throws Exception {
			String[] parts = exchange.getIn().getBody(String.class).split(",");
            
            exchange.getIn().setBody(new Order(
            		parts[0], 
            		parts[1], 
            		Integer.parseInt(parts[2]), 
            		Integer.parseInt(parts[3]))
            );
			
		}
		
	};

	public static void main(String[] args) throws JMSException {
		DefaultCamelContext context = new DefaultCamelContext();
		ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
        activeMQComponent.setTrustAllPackages(true);
        context.addComponent("activemq", activeMQComponent);
		
        
        ActiveMQConnectionFactory fac = new ActiveMQConnectionFactory();
		Connection con = fac.createConnection();
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue webQueue = session.createQueue("webQueue");
		
		RouteBuilder route = new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("stream:in")
                .split(body().tokenize("\n"))
                .process(orderFactory)
                .to("activemq:queue:webQueue");
				
			}
			
		};
		
	}
	
}
