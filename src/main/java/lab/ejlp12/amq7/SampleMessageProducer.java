package lab.ejlp12.amq7;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class SampleMessageProducer {

    public static void main(String[] args) throws Exception {

        int NUM_MSGS = 1000;
        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");

        try {

            // Step 1. Create an amqp qpid 1.0 connection
            connection = connectionFactory.createConnection();

            // Step 2. Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Step 3. Create a sender
            Queue queue = session.createQueue("exampleQueue");
            MessageProducer sender = session.createProducer(queue);

            // Step 4. send a few simple message
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < NUM_MSGS; i++) {
                message.setText("This is message " + (i + 1) + " from producer");
                System.out.println("Sending message: " + message.getText());
                sender.send(message);
                Thread.sleep(1000);
            }

            // Step 5. Start delivery of message
            connection.start();

        } finally {
            if (connection != null) {
                // Step 9. close the connection
                connection.close();
            }
        }
    }

}
