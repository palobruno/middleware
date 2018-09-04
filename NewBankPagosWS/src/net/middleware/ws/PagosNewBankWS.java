package net.middleware.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.middleware.*;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PagosNewBankWS {

	@WebMethod
	public PagosNewBankResponse addPerson(PagosNewBankRequest p);
	
}