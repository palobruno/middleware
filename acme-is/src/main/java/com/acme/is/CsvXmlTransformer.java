package com.acme.is;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;

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
			xmlResults += lineXml + "\r\n";
		}
	
		return MessageBuilder.withPayload(xmlResults).copyHeaders(csvResults.getHeaders()).build();
	}
	
	public Message<String> xmlToCsv(Message<String> xmlMsg) throws Exception{
		
		String res = "";
		
		try {
			Source xmlSrc = new StreamSource(new StringReader(xmlMsg.getPayload()));
			ClassLoader classLoader = getClass().getClassLoader();
			File xsltFile = new File(classLoader.getResource("XSLs/xml_to_csv.xslt").getFile());
			Source xsltSrc = new StreamSource(xsltFile);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(xsltSrc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			transformer.transform(xmlSrc, result);
			res = writer.toString();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return MessageBuilder.withPayload(res).copyHeaders(xmlMsg.getHeaders()).build();
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
