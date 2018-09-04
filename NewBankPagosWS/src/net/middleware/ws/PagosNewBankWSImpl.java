package net.middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "net.middleware.PagosNewBankWS")  
public class PagosNewBankWSImpl implements PagosNewBankWS {

	@Override
	public PagosNewBankResponse addPerson(PagosNewBankRequest p) {
		PagosNewBankResponse response = new PagosNewBankResponse();
		response.idTransaccion = "1234";
		response.referencia = p.referencia;
		return response;
		
	}

}