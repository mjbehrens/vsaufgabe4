package de.tuberlin.cit.vs.orderProcessing;

import java.io.IOException;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CallCenterOrderSystem  {
	
	
	public void main(String[] args) {
	
		ActiveMQConnectionFactory conFactory = new ActiveMQConnectionFactory();
        conFactory.setTrustAllPackages(true);
        try {
			Connection con = conFactory.createConnection();
			Scanner in = new Scanner(System.in);
			con.start();
			while(!in.nextLine().equals("quit"))
			{
			System.out.println("please give your name in this format (firstName LastName nbrofsurfboards nbrOfDivingSuits)");
			in.nextLine();
			}
			con.close();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
       
	
	
	
    
    
	}
}
