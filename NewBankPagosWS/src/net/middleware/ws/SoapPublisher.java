package net.middleware.ws;

import javax.xml.ws.Endpoint;
import net.middleware.ws.PagosNewBankWSImpl;;

public class SoapPublisher {

	public static void main(String[] args) {
		 Endpoint.publish("http://localhost:8888/ws/newbank", new PagosNewBankWSImpl());  
	}

}
