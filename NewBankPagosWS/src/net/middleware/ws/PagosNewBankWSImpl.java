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
import net.middleware.ws.Utils;

import org.json.*;

@WebService(endpointInterface = "net.middleware.ws.PagosNewBankWS")  
public class PagosNewBankWSImpl implements PagosNewBankWS {
	
	public final static Logger LOGGER = Logger.getLogger("new-bank");
	public static int transactionId = 0;
	public static int OK = 0;
	public static int ERROR = 1;

	@Override
	public String makePayments(String paymentsJson) {
		System.out.println("RECIBIDO: " + paymentsJson);
		
		JSONObject obj = new JSONObject(paymentsJson);
		JSONObject objPaymentList = obj.getJSONObject("paymentList");
		JSONArray payments = objPaymentList.getJSONArray("payment");
		
		for(int i = 0; i < payments.length(); i++) {
			JSONObject payment = payments.getJSONObject(i);
			LOGGER.log(Level.INFO, "Pago procesado: " + payment.getInt("referenceNumber"));
		}

		return paymentsJson;
	}

}