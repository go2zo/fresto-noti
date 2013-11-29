package com.fresto.noti.mail.internal;

import com.fresto.noti.mail.core.MailException;
import com.fresto.noti.mail.domain.Mail;
import com.fresto.noti.mail.service.MailTemplateManager;

public class VelocityMailTemplateManager implements MailTemplateManager {

	@Override
	public void reload() throws MailException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSubject(Mail mail) throws MailException {
		// TODO
		return mail.getSubjectData();
	}

	@Override
	public String getContent(Mail mail) throws MailException {
		// TODO
		return mail.getContentData();
	}

}
