package com.acme.is;

import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;

public class FilesReleaseStrategy implements ReleaseStrategy  {

	@Override
	public boolean canRelease(MessageGroup group) {
		return group.size() == 2;
	}


}
