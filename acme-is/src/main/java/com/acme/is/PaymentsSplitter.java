package com.acme.is;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class PaymentsSplitter extends AbstractMessageSplitter {

	@Override
	protected List<Message<String>> splitMessage(Message<?> message) {
		List<Message<String>> messages = new ArrayList<Message<String>>();
		String paymentListStr = (String) message.getPayload();

		Document xmlDocument;
		try {
			xmlDocument = Utils.xmlDocFromString(paymentListStr);
			XPath xPath = XPathFactory.newInstance().newXPath();
			Message<String> msg;
			
			String packRefId = ((String) xPath.compile("string(/paymentList/id)").evaluate(xmlDocument, XPathConstants.STRING));

			for (int i = 1; i <= 3; i++) {
				String bankiMsg = "";
				String expr = "/paymentList/payment[bank=" + i + "]";
				NodeList nodeList = (NodeList) xPath.compile(expr).evaluate(xmlDocument, XPathConstants.NODESET);
				if (nodeList.getLength() > 0) {
					//arma una lista para los pagos que van a newBank y oldBank, los que van a BD acme van separados
					if (i != BankType.Acme) {
						for (int j = 0; j < nodeList.getLength(); j++) {
							bankiMsg += "\n\t" + Utils.xmlNodeToString(nodeList.item(j));
						}
						bankiMsg = "<paymentList>" + bankiMsg + "\n</paymentList>";
						msg = MessageBuilder.withPayload(bankiMsg).setHeader("packRefId", packRefId).build();
						messages.add(msg);
					} else {
						for (int j = 0; j < nodeList.getLength(); j++) {
							bankiMsg = "<paymentList>\n\t" + Utils.xmlNodeToString(nodeList.item(j)) + "\n</paymentList>";
							msg = MessageBuilder.withPayload(bankiMsg).setHeader("packRefId", packRefId).build();
							messages.add(msg);
						}
					}
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return messages;
	}

}
