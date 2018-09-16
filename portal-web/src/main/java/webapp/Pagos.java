package webapp;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/pagos")
public class Pagos extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	};

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pagos = request.getParameter("pagos");
		Productor p = new Productor();

		try {
			p.create("middleware-queue-in");
			p.enviarPagos(pagos);
			String path = "/WEB-INF/views/home.jsp";
			request.setAttribute("mensaje", "pagos enviados");
			request.getRequestDispatcher(path).forward(request, response);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	};
}
