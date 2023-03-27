package lt.eif.viko.mdanys.travelagency.util;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class MessageUtil {

    private static final String QUEUE_NAME = "XML";

    public static void messageSender(String message) throws JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(destination);
        TextMessage sendMessage = session.createTextMessage(message);
        producer.send(sendMessage);
    }

}
