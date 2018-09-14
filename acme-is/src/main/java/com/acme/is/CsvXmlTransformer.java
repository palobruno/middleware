package com.acme.is;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class CsvXmlTransformer {

	public Message<String> csvToXml(Message<String> csvResults) {
		String csv = csvResults.getPayload();
		
		String[] lines = csv.split("\r\n");
		String xmlResults = "";
		String lineXml;
		
		for(String line: lines) {
			lineXml = lineToXml(line);
			xmlResults += lineXml;
		}
	
		return MessageBuilder.withPayload(xmlResults).copyHeaders(csvResults.getHeaders()).build();
	}
	
	public Message<String> xmlToCsv(Message<String> xmlMsg) {
		return xmlMsg;
	}
	
	private String lineToXml(String line) {
		String xmlResult;
		String[] fields = line.split(",");
		
		String statusCode = fields[0];
		String description = fields[1];
		String referenceId = fields[2];
		String transactionId = fields[3];
		
		xmlResult = "<paymentResult>";
		xmlResult += "<statusCode>" + statusCode + "</statusCode>";
		xmlResult += "<description>" + description + "</description>";
		xmlResult += "<referenceId>" + referenceId + "</referenceId>";
		xmlResult += "<transactionId>" + transactionId + "</transactionId>";
		xmlResult += "</paymentResult>";
		
		return xmlResult;
	}
}
