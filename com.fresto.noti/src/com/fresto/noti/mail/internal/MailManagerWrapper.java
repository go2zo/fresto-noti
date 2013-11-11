package com.fresto.noti.mail.internal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fresto.noti.mail.domain.Mail;
import com.fresto.noti.mail.service.MailManager;

public class MailManagerWrapper implements MailManager {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public MailManager manager; 
	
	public MailManagerWrapper(MailManager manager) {
		this.manager = manager;
	}
	
	@Override
	public List<Mail> getMailList(String daemonName) {
		if (manager != null) {
			return manager.getMailList(daemonName);
		}
		return new ArrayList<>();
	}

	@Override
	public int updateMail(Mail mail) {
		logger.debug("updateMail()");
		return 0;
	}

}
