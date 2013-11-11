package com.fresto.noti.mail.service;

import com.fresto.noti.mail.core.MailException;
import com.fresto.noti.mail.domain.Mail;

public interface MailTemplateManager {

	void reload() throws MailException;
	
	String getSubject(Mail mail) throws MailException;
	String getContent(Mail mail) throws MailException;
}
