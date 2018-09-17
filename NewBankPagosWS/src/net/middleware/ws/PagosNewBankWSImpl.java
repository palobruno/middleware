package net.middleware.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.middleware.ws.PagosNewBankWS;
import net.middleware.ws.PagosNewBankRequest;
import net.middleware.ws.PagosNewBankResponse;
import net.middleware.ws.Utils;

@WebService(endpointInterface = "net.middleware.ws.PagosNewBankWS")  
public class PagosNewBankWSImpl implements PagosNewBankWS {
	
	public final static Logger LOGGER = Logger.getLogger("new-bank");
	public static int transactionId = 0;
	public static int OK = 0;
	public static int ERROR = 1;

	@Override
	public String makePayments(String paymentsXml) {
		System.out.println("RECIBIDO: " + paymentsXml);
		
		String response = "<paymentResultList>";
		String description = "Pago realizado con exito";
		String referencia = "";
		/*
		try {
			Document dom = Utils.xmlDocFromString(paymentsXml);
			NodeList nl = dom.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nl.getLength(); i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) nl.item(i);
	                if (el.getNodeName().equals("payment")) {
	                	referencia = el.getElementsByTagName("referenceNumber").item(0).getTextContent();
			            
	                	response += "<paymentResult>" +
	                			"<statusCode>" + OK + "</statusCode>" +
	                			"<description>" + description + "</description>" +
	                			"<transactionId>" + transactionId + "</transactionId>" +
	                			"<referenceId>" + referencia + "</referenceId>";
	                	
			            break;
	                }
	            }
			}
			
			response += "</paymentResultList>";
			
			LOGGER.log(Level.INFO, "Pago procesado: " + referencia);

		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Error procesando pago: " + referencia);
			
			e.printStackTrace();
		}
		*/
		return response;
	}

}