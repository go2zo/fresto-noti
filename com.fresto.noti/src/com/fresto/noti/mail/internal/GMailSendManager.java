package com.fresto.noti.mail.internal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fresto.noti.mail.SMTPMailSendManager;

public class GMailSendManager extends SMTPMailSendManager {

	private Logger logger = LoggerFactory.getLogger(GMailSendManager.class);

	@Override
	protected Properties getProperties() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("gmail.properties")); //$NON-NLS-1$
		} catch (FileNotFoundException e) {
			logger.debug(e.getMessage());
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
		return props;
	}
}
