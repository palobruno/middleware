import java.io.IOException;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorld extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Postprocess request: gather and validate submitted data and display the result in the same JSP.
    	try {
	    	ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	    	Connection connection = factory.createConnection();
	    	Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
	    	//Destination queue = session.createQueue("clientQueue");
	    	MessageProducer publisher = session.createProducer(queue);
	    	connection.start();
	
	    	Message message = null;
	    	message = session.createTextMessage("Text Message");
	    	publisher.send(message);
	    	
	    	
	        // Prepare messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        request.setAttribute("messages", messages);
	
	        // Get and validate name.
	        String msg = request.getParameter("msg");
	
	        // No validation errors? Do the business job!
	        if (messages.isEmpty()) {
	            messages.put("success", String.format("Hola, tu mensaje es %s!", msg));
	        }
	
	        request.getRequestDispatcher("Bye.jsp").forward(request, response);
    	}
    	catch (Exception ex) {
    		System.out.println("fueeeego");
    	}
    }
}