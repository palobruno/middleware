package net.middleware.ws;

import javax.jws.WebMethod;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import net.middleware.*;

import org.w3c.dom.Document;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PagosNewBankWS {

	@WebMethod
	public String makePayments(String p);
	
}