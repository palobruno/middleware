package com.acme.is;

import java.io.StringWriter;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.acme.is.PagosNewBankRequest;

import org.json.JSONObject;
import org.json.XML;

public class XmlSoapTransformer {

	public String xmlToSoap(Message<String> msgXml) {
		String soapRequest;
		String xmlPayments = msgXml.getPayload();
		
		System.out.println("PA MANDAR: " + xmlPayments);
		
		PagosNewBankRequest req = new PagosNewBankRequest();
		req.setReferencia("1234");
		
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlPayments);
			String jsonString = xmlJSONObj.toString();
			
			soapRequest = "<ws:makePayments xmlns:ws=\"http://ws.middleware.net/\">" + 
					"<arg0>" + jsonString + "</arg0>" + 
					"</ws:makePayments>";
			
			return soapRequest;
		} catch (Exception e) {
			
		}
		
		
		
		return null;
	}
	
	public Message<String> soapToXml(Message<PagosNewBankResponse> response) {
		String xml = "";
		
		return MessageBuilder.withPayload(xml).copyHeaders(response.getHeaders()).build();
	}
	
}
