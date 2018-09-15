package com.acme.is;

import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class XmlDbTransformer {

	public Message<Map<String, Object>> xmlToDb(Message<String> msg) {
		Map<String, Object> attrsMap = new HashMap<>();
		String referencia = "";
		
		try {
			Document dom = Utils.xmlDocFromString(msg.getPayload());
			NodeList nl = dom.getDocumentElement().getChildNodes();
			
			
			for (int i = 0; i < nl.getLength(); i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) nl.item(i);
	                if (el.getNodeName().equals("payment")) {
	                	String[] orig = el.getElementsByTagName("originAccout").item(0).getTextContent().split("-");
	                	String[] dest = el.getElementsByTagName("destinationAccount").item(0).getTextContent().split("-");
	                	long sucursal_origen = Long.parseLong(orig[0]);
	                	long cuenta_origen = Long.parseLong(orig[1]);
	                	long sucursal_destino = Long.parseLong(dest[0]);
	                	long cuenta_destino = Long.parseLong(dest[1]);
			            String fecha = el.getElementsByTagName("datetime").item(0).getTextContent().substring(0, 10);
			            String hora = el.getElementsByTagName("datetime").item(0).getTextContent().substring(11, 19);
			            int moneda = Integer.parseInt(el.getElementsByTagName("currency").item(0).getTextContent());
			            double importe = Double.parseDouble(el.getElementsByTagName("amount").item(0).getTextContent());
			            referencia = el.getElementsByTagName("referenceNumber").item(0).getTextContent();
			            String observaciones = el.getElementsByTagName("additionalInformation").item(0).getTextContent();
			            
			            attrsMap.put("sucursal_origen", sucursal_origen);
			            attrsMap.put("cuenta_origen", cuenta_origen);
			            attrsMap.put("sucursal_destino", sucursal_destino);
			            attrsMap.put("cuenta_destino", cuenta_destino);
			            attrsMap.put("fecha", fecha);
			            attrsMap.put("hora", hora);
			            attrsMap.put("moneda", moneda);
			            attrsMap.put("importe", importe);
			            attrsMap.put("referencia", referencia);
			            attrsMap.put("observaciones", observaciones);
			            
			            break;
	                }
	            }
			}            

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MessageBuilder.withPayload(attrsMap).copyHeaders(msg.getHeaders()).setHeader("refPago", referencia).build();
	}
	
	
	public Message<String> dbToXml(Message<Map<String, Object>> msg) {
		
		String xmlDbRes;
		String statusCode;
		String description;
		String referenceId;
		long transactionId;
		
		if (msg.getPayload().get("id") == null) {
			statusCode = "1";
			description = "Error al procesar pago";
			referenceId = msg.getHeaders().get("refPago").toString();
			transactionId = -1;
		} else {
			statusCode = "0";
			description = "Pago procesado con exito";
			referenceId = msg.getHeaders().get("refPago").toString(); //
			transactionId = (long) msg.getPayload().get("id");
		}
		
		xmlDbRes = "<paymentResult>"
		         + "<statusCode>"    + statusCode    + "</statusCode>"
		         + "<description>"   + description   + "</description>"
				 + "<referenceId>"   + referenceId   + "</referenceId>"
		         + "<transactionId>" + transactionId + "</transactionId>"
		         + "</paymentResult>";
		
		return MessageBuilder.withPayload(xmlDbRes).copyHeaders(msg.getHeaders()).build();
	}
	
	
	
	
}
