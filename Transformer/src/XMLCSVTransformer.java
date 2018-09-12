package com.acme.is;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.integration.xml.transformer.AbstractXmlTransformer;
import org.springframework.messaging.Message;

public class XMLCSVTransformer extends AbstractXmlTransformer {


	protected Message doTransform(Message message) throws Exception {

		String xml = (String) message.getPayload();
		Source xmlSource = new StreamSource(new StringReader(xml));

		Source xslt = new StreamSource(
				new File("E:\\Downloads\\middleware-master\\acme-is\\src\\main\\resources\\xmltocsv.xslt"));
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xslt);

		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		transformer.transform(xmlSource, result);
		String string = writer.toString();
		return MessageBuilder.withPayload(string).build();

	}
}