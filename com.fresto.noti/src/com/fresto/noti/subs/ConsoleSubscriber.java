package com.fresto.noti.subs;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleSubscriber extends SubscriberImpl {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void update(Object[] rows) {
		logger.info(MessageFormat.format((String)params.get(CONTENT), rows));
	}

}
