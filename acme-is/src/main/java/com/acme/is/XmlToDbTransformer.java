package com.acme.is;

import java.util.HashMap;
import java.util.Map;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class XmlToDbTransformer {

	public Message<Map<String, Object>> xmlToDb(Message<String> msg) {
		Map<String, Object> attrsMap = new HashMap<>();
		try {
			Document dom = Utils.xmlDocFromString(msg.getPayload());
			NodeList nl = dom.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nl.getLength(); i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) nl.item(i);
	                if (el.getNodeName().equals("payment")) {
			            long sucursal_origen = Long.parseLong(el.getElementsByTagName("originAccout").item(0).getTextContent().substring(0, 3));
			            long cuenta_origen = Long.parseLong(el.getElementsByTagName("originAccout").item(0).getTextContent().substring(4, 10));
			            long sucursal_destino = Long.parseLong(el.getElementsByTagName("destinationAccount").item(0).getTextContent().substring(0, 3));
			            long cuenta_destino = Long.parseLong(el.getElementsByTagName("destinationAccount").item(0).getTextContent().substring(4, 10));
			            String fecha = el.getElementsByTagName("datetime").item(0).getTextContent().substring(0, 10);
			            String hora = el.getElementsByTagName("datetime").item(0).getTextContent().substring(11, 19);
			            int moneda = Integer.parseInt(el.getElementsByTagName("currency").item(0).getTextContent());
			            double importe = Double.parseDouble(el.getElementsByTagName("amount").item(0).getTextContent());
			            String referencia = el.getElementsByTagName("referenceNumber").item(0).getTextContent();
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
		
		return MessageBuilder.withPayload(attrsMap).copyHeaders(msg.getHeaders()).build();
	}
	
}
