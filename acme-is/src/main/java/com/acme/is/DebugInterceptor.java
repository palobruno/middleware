package com.acme.is;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

//No está funcionando, no para en los breakpoints
public class DebugInterceptor implements ChannelInterceptor {

	@Override
	public Message<?> postReceive(Message<?> message, MessageChannel channel) {
		
		System.out.println("[DebugInterceptor][channel:" + channel.toString() + "][headers:" + message.getHeaders() + "][payload:" + message.getPayload() + "]");
		
		return ChannelInterceptor.super.postReceive(message, channel);
	}

	@Override
	public boolean preReceive(MessageChannel channel) {

		return ChannelInterceptor.super.preReceive(channel);
	}

	
	
}
