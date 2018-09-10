package webapp;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/consulta")
public class Consulta extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Consumidor cons = new Consumidor();
		
		try {
			cons.create("middleware-queue-in");
			String res = cons.consultarPagos(1000);
			cons.closeConnection();
			String path = "/WEB-INF/views/home.jsp";
			
			request.setAttribute("respuesta", res);
			
			request.getRequestDispatcher(path).forward(request, response);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
