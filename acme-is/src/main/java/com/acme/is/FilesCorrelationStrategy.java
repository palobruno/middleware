package com.acme.is;

import org.springframework.integration.aggregator.CorrelationStrategy;
import org.springframework.messaging.Message;


public class FilesCorrelationStrategy implements CorrelationStrategy {
	@Override
	public Object getCorrelationKey(Message<?> message) {
		
		return message.getHeaders().get("packRefId");
	}
}
