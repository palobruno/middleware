package com.acme.is;

import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;
import java.util.List;
import java.io.IOException;

public class CsvXmlTransformer {

	public String csvToXml(Message<String> csvResults) {
		IntegrationMessageHeaderAccessor accessor = new IntegrationMessageHeaderAccessor(csvResults);
		String csv = csvResults.getPayload();
		
		String[] lines = csv.split("\r\n");
		String xmlResults = "";
		String lineXml;
		
		for(String line: lines) {
			lineXml = lineToXml(line);
			xmlResults += lineXml;
		}
	
		return xmlResults;
	}
	
	public String lineToXml(String line) {
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
