package com.acme.is;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.acme.is.PagosNewBankRequest;

public class XmlSoapTransformer {

	public Message<PagosNewBankRequest> xmlToSoap(Message<String> msgXml) {
		PagosNewBankRequest request = new PagosNewBankRequest();
		
		try {
			Document dom = Utils.xmlDocFromString(msgXml.getPayload());
			NodeList nl = dom.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nl.getLength(); i++) {
	            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
	                Element el = (Element) nl.item(i);
	                if (el.getNodeName().equals("payment")) {
			            String cuentaOrigen = el.getElementsByTagName("originAccout").item(0).getTextContent().substring(4, 10);
			            String cuentaDestino = el.getElementsByTagName("destinationAccount").item(0).getTextContent().substring(4, 10);
			            String fecha = el.getElementsByTagName("datetime").item(0).getTextContent().substring(0, 10);
			            String hora = el.getElementsByTagName("datetime").item(0).getTextContent().substring(11, 19);
			            int moneda = Integer.parseInt(el.getElementsByTagName("currency").item(0).getTextContent());
			            double importe = Double.parseDouble(el.getElementsByTagName("amount").item(0).getTextContent());
			            String referencia = el.getElementsByTagName("referenceNumber").item(0).getTextContent();
			            String observaciones = el.getElementsByTagName("additionalInformation").item(0).getTextContent();
			            
			            Date fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fecha + " " + hora);
			            
			            request.setCuentaOrigen(cuentaOrigen);
			            request.setCuentaDestino(cuentaDestino);
			            request.setFecha(fechaHora);
			            request.setMoneda(moneda);
			            request.setImporte(importe);
			            request.setReferencia(referencia);
			            request.setObservaciones(observaciones);
			            
			            break;
	                }
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MessageBuilder.withPayload(request).copyHeaders(msgXml.getHeaders()).build();
	}
	
	public Message<String> soapToXml(Message<PagosNewBankResponse> response) {
		String xml = "";
		
		return MessageBuilder.withPayload(xml).copyHeaders(response.getHeaders()).build();
	}
	
}
