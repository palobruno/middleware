package com.acme.is;

import java.util.List;

import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;


public class PaymentsAggregator {
	
	public Message<String> add(List<Message<String>> results) {
		IntegrationMessageHeaderAccessor accessor = new IntegrationMessageHeaderAccessor(results.get(0));
		String addedResults = "<paymentList>\n    <id>" + accessor.getHeader("packRefId") + "</id>";
		for (Message<String> r : results) {
			addedResults += "\n    " + r.getPayload();
		}
		addedResults += "\n</paymentList>";
		return MessageBuilder.withPayload(addedResults).build();
	}
	
}
