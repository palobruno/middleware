package com.acme.is;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class PaymentsSplitter extends AbstractMessageSplitter {

	/*
	 * Divide el xml entrante según banco.
	 * Retorna una lista de a lo sumo 3 Message cuyo payload es el xml para cada banco.
	 * En el header de cada Message se setea el CORRELATION_ID como el id del paquete de pagos y
	 * se setea la cantidad de pagos.
	 */
	
	@Override
	protected List<Message<String>> splitMessage(Message<?> message) {
		List<Message<String>> messages = new ArrayList<Message<String>>();
		String paymentListStr = (String) message.getPayload();

		Document xmlDocument;
		try {
			xmlDocument = loadXMLFromString(paymentListStr);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			long packRefId = Integer.parseInt((String) xPath.compile("string(/paymentList/id)").evaluate(xmlDocument, XPathConstants.STRING));

			for (int i = 1; i <= 3; i++) {
				String bankiMsg = "<paymentList>\n\t";
				String expr = "/paymentList/payment[bank=" + i + "]";
				NodeList nodeList = (NodeList) xPath.compile(expr).evaluate(xmlDocument, XPathConstants.NODESET);
				if (nodeList.getLength() > 0) {
					for (int j = 0; j < nodeList.getLength(); j++) {
						bankiMsg += nodeToString(nodeList.item(j)) + "\n\t";
					}
					bankiMsg += "</paymentList>";
					Message<String> msg = MessageBuilder.withPayload(bankiMsg)
							.setHeader("packRefId", packRefId).build();
					messages.add(msg);
				}
			}

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messages;
	}

	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return builder.parse(is);
	}

	private String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			System.out.println("nodeToString Transformer Exception");
		}
		return sw.toString();
	}

}
