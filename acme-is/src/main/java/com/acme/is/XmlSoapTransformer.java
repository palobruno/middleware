package com.acme.is;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class XmlSoapTransformer {

	public String xmlToSoap(Message<String> msgXml) {
		String soapRequest;
		String xmlPayments = msgXml.getPayload();
		
		System.out.println("PA MANDAR: " + xmlPayments);
		
		soapRequest = "<ws:makePayments xmlns:ws=\"http://ws.middleware.net/\">" + 
				"         <arg0>" + xmlPayments + "</arg0>" + 
				"      </ws:makePayments>";
		
		//return MessageBuilder.withPayload(soapRequest).copyHeaders(msgXml.getHeaders()).build();
		return soapRequest;
	}
	
	public Message<String> soapToXml(Message<PagosNewBankResponse> response) {
		String xml = "";
		
		return MessageBuilder.withPayload(xml).copyHeaders(response.getHeaders()).build();
	}
	
}
