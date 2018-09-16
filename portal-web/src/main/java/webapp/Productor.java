package webapp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Productor {
	private Connection connection;
	private Session session;
	private MessageProducer messageProducer;

	public void create(String destinationName) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(destinationName);
		messageProducer = session.createProducer(destination);
	}

	public void close() throws JMSException {
		connection.close();
	}

	public void enviarPagos(String xmlPagos) throws JMSException {
		TextMessage textMessage;
		textMessage = session.createTextMessage(xmlPagos);
		messageProducer.send(textMessage);

	}
}