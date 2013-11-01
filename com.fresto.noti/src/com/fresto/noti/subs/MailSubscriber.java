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
import java.util.Map;

import com.fresto.noti.mail.GMailSender;

public class MailSubscriber implements Subscriber {

	private static final String FROM = "from"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String PASSWORD = "password"; //$NON-NLS-1$
	private static final String TO = "to"; //$NON-NLS-1$
	
	private Map<?, ?> params;
	
	@Override
	public void setIdentifier(String id) {
	}

	@Override
	public void putParams(Map<?, ?> params) {
		this.params = params;
	}
	
	public void update(Object[] row) {
		GMailSender mailSender = new GMailSender();
		
		String from = (String)params.get(FROM);
		String name = (String)params.get(NAME);
		String pw = (String)params.get(PASSWORD);
		String to = (String)params.get(TO);
		String subject = (String)params.get(SUBJECT);
		String message = (String)params.get(MESSAGE);
		message = MessageFormat.format(message, row);
		
		mailSender.sendMail(from, pw, name, to, subject, message);
	}

}
