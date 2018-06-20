package de.tuberlin.cit.vs.validator;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import de.tuberlin.cit.vs.shared.Vote;

public class Validator {
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory conFactory = new ActiveMQConnectionFactory();
            conFactory.setTrustAllPackages(true);
            Connection con = conFactory.createConnection();

            final Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue inQueue = session.createQueue("validationIn");
            Queue outQueue = session.createQueue("validationOut");
            MessageConsumer consumer = session.createConsumer(inQueue);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        Vote vote = (Vote)((ObjectMessage)message).getObject();

                        System.out.println(vote);

                        ObjectMessage answer = session.createObjectMessage(vote);
                        boolean validated = Math.random() > 0.5;
                        answer.setBooleanProperty("validated", validated);

                        MessageProducer producer = session.createProducer(outQueue);
                        producer.send(answer);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            con.start();
            System.in.read();
            con.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
