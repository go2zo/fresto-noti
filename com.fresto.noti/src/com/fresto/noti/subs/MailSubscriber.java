/**************************************************************************************
 * Copyright 2013 TheSystemIdeas, Inc and Contributors. All rights reserved.          *
 *                                                                                    *
 *     https://github.com/owlab/fresto                                                *
 *                                                                                    *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * This file is licensed under the Apache License, Version 2.0 (the "License");       *
 * you may not use this file except in compliance with the License.                   *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 * 
 **************************************************************************************/
package com.fresto.noti.subs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fresto.noti.mail.SMTPMailSendManager;
import com.fresto.noti.mail.core.MailException;
import com.fresto.noti.mail.core.MailStatusCode;
import com.fresto.noti.mail.core.MessageException;
import com.fresto.noti.mail.domain.Mail;
import com.fresto.noti.mail.internal.GMailSendManager;
import com.fresto.noti.mail.internal.MailManagerWrapper;
import com.fresto.noti.mail.internal.VelocityMailTemplateManager;
import com.fresto.noti.mail.service.MailManager;
import com.fresto.noti.mail.service.MailTemplateManager;

public class MailSubscriber implements Subscriber, MailManager {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String PROVIDER = "provider"; //$NON-NLS-1$
	private static final String FROM = "from"; //$NON-NLS-1$
	private static final String USERNAME = "username"; //$NON-NLS-1$
	private static final String PASSWORD = "password"; //$NON-NLS-1$
	private static final String TO = "to"; //$NON-NLS-1$
	
	private String daemonName;
	
	private Map<?, ?> params;
	
	@Override
	public void setIdentifier(String id) {
	} 
	@Override
	public void putParams(Map<?, ?> params) {
		this.params = params;
	}
	
	public void update(Object[] row) {
		daemonName = (String)params.get(PROVIDER);

		MailManager mailManager = new MailManagerWrapper(this);
		MailTemplateManager mailTemplateManager = new VelocityMailTemplateManager();
		SMTPMailSendManager mailSendManager = null;
		if ("gmail".equals(daemonName)) { //$NON-NLS-1$
			mailSendManager = new GMailSendManager();
		} else {
			mailSendManager = new SMTPMailSendManager();
		}
		mailSendManager.setUserName((String) params.get(USERNAME));
		mailSendManager.setPassword((String) params.get(PASSWORD));

		mailTemplateManager.reload();
		List<Mail> mailList = mailManager.getMailList(daemonName);
		
		if (mailList != null) {
			String subject = null;
			String content = null;
			for (Mail mail : mailList) {
				try {
					subject = mailTemplateManager.getSubject(mail);
					content = mailTemplateManager.getContent(mail);

					mail.setStatusCode(MailStatusCode.SEND_READY);
					// send
					Date date = new Date();
					mailSendManager.send(mail.getRecipients(), mail.getSender(), date, subject, content);
					mail.setStatusCode(MailStatusCode.SEND_OK);
					mail.setSentDate(date);
				} catch (MailException e) {
					mail.setStatusCode(e.getStatusCode());
					logger.warn("메일 발송 중 에러가 발생했습니다.", e);
				} catch (MessageException e) {
					mail.setStatusCode(MailStatusCode.UNKOWN_ERROR);
					logger.warn("메일 발송 중 에러가 발생했습니다.", e);
				} catch (Exception e) {
					mail.setStatusCode(MailStatusCode.UNKOWN_ERROR);
					logger.warn("메일 발송 중 에러가 발생했습니다.", e);
				} finally {
					mail.setUpdatedBy(daemonName);
					mail.setUpdatedDate(new Date());
					mailManager.updateMail(mail);
				}
			}
		}
	}
	
	@Override
	public List<Mail> getMailList(String daemonName) {
		List<Mail> mailList = new ArrayList<>();
		
		String from = (String)params.get(FROM);
		String to = (String)params.get(TO);
		String subject = (String)params.get(SUBJECT);
		String content = (String)params.get(CONTENT);
		
		logger.debug(MessageFormat.format("\r\nProvider:{0}\r\nFrom:{1}\r\nTo:{2}\r\nSubject:{3}\r\nContent:{4}",
				daemonName, from, to, subject, content));
		
		Mail mail = new Mail();
		mail.setSender(from);
		mail.setRecipients(to);
		mail.setSubjectData(subject);
		mail.setContentData(content);

		mailList.add(mail);
		
		return mailList;
	}
	
	@Override
	public int updateMail(Mail mail) {
		// Not work.
		return 0;
	}

}
