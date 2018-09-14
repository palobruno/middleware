package com.acme.is;

import java.io.File;
import java.util.List;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;


public class FilesAggregator {
	
	public Message<File> add(List<Message<File>> results) {
		
		Message<File> msgFile1 = results.get(0);
		Message<File> msgFile2 = results.get(1);
		File resFile;
		MessageHeaders resHeaders;
		
		if (msgFile1.getHeaders().containsKey("outFile")) {
			resFile = msgFile1.getPayload();
			resHeaders = msgFile2.getHeaders();
		} else {
			resFile = msgFile2.getPayload();
			resHeaders = msgFile1.getHeaders();
		}
		
		return MessageBuilder.withPayload(resFile).copyHeaders(resHeaders).build();
	}
	
}
