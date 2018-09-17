package com.acme.is;

import java.io.StringWriter;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.acme.is.PagosNewBankRequest;

import org.json.JSONObject;
import org.json.XML;

public class XmlSoapTransformer {

	public String xmlToSoap(Message<String> msgXml) {
		String soapRequest;
		String xmlPayments = msgXml.getPayload();
		
		try {
			JSONObject xmlJSONObj = XML.toJSONObject(xmlPayments);
			String jsonString = xmlJSONObj.toString();
			
			soapRequest = "<ws:makePayments xmlns:ws=\"http://ws.middleware.net/\">" + 
					"<arg0>" + jsonString + "</arg0>" + 
					"</ws:makePayments>";
			
			return soapRequest;
		} catch (Exception e) {}
		
		return null;
	}
	
	public Message<String> soapToXml(Message<String> response) {
		String xml = "<payments>";
		
		System.out.println("RESP: " + response.getPayload());
		
		try {
			Document dom = Utils.xmlDocFromString(response.getPayload());
			NodeList nl = dom.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nl.getLength(); i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) nl.item(i);
	                System.out.println("NODENAME: " + el.getNodeName());
	                if (el.getNodeName().equals("return")) {
	                	String paymentList = el.getTextContent();
	                	xml += paymentList;
	                }
	            }
			}
		
			xml += "</payments>";
		} catch(Exception e) {}
		
		return MessageBuilder.withPayload(xml).copyHeaders(response.getHeaders()).build();
	}
	
}
