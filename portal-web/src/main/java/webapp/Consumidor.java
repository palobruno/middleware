package webapp;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumidor {
	Connection connection;
	MessageConsumer messageConsumer;

	public void create(String queueName) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
		this.connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		Queue queue = session.createQueue(queueName);
		messageConsumer = session.createConsumer(queue);
		connection.start();
	}

	public void closeConnection() throws JMSException {
		connection.close();
	}

	public String consultarPagos(int timeout) throws JMSException {
		Message message = messageConsumer.receive(timeout);
		String res = "No hay respuesta.";
		if (message != null) {
			TextMessage textMessage = (TextMessage) message;
			res = textMessage.getText();
			message.acknowledge();
		}
		return res;
	}
}
