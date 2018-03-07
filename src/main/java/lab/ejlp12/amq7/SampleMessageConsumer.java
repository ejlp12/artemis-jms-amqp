package lab.ejlp12.amq7;

import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;

public class SampleMessageConsumer {

    public static void main(String[] args) throws Exception {

        Connection connection = null;
        ConnectionFactory connectionFactory = new JmsConnectionFactory("amqp://localhost:5672");

        try {

            // Step 1. Create an amqp qpid 1.0 connection
            connection = connectionFactory.createConnection();

            // Step 2. Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Step 3. Create a sender
            Queue queue = session.createQueue("exampleQueue");
            MessageConsumer consumer = session.createConsumer(queue);

            connection.start();

            // Step 4. send a few simple message
            TextMessage message;
            while (true) {
                Message m = consumer.receive(1);
                if (m != null) {
                    if (m instanceof TextMessage) {
                        message = (TextMessage) m;
                        System.out.println("Reading message: " + message.getText());
                    } else {
                        break;
                    }
                }
            }


        } finally {
            if (connection != null) {
                // Step 9. close the connection
                connection.close();
            }
        }
    }

}
