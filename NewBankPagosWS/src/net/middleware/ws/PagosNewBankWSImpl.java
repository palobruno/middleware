package net.middleware.ws;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

import net.middleware.ws.PagosNewBankWS;
import net.middleware.ws.PagosNewBankRequest;
import net.middleware.ws.PagosNewBankResponse;

@WebService(endpointInterface = "net.middleware.ws.PagosNewBankWS")  
public class PagosNewBankWSImpl implements PagosNewBankWS {
	
	public final static Logger LOGGER = Logger.getLogger("new-bank");

	@Override
	public PagosNewBankResponse makePayments(PagosNewBankRequest p) {
		PagosNewBankResponse response = new PagosNewBankResponse();
		
		response.idTransaccion = "1234";
		//response.referencia = p.referencia;
		
		LOGGER.log(Level.INFO, "Procesando pago NewBank");
		
		return response;
	}

}