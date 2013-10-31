package com.fresto.noti.subs;

import java.util.Map;

import com.fresto.noti.mail.GMailSender;

public class MailSubscriber implements Subscriber {

	private static final String FROM = "from"; //$NON-NLS-1$
	private static final String NAME = "name"; //$NON-NLS-1$
	private static final String PASSWORD = "password"; //$NON-NLS-1$
	private static final String TO = "to"; //$NON-NLS-1$
	private static final String SUBJECT = "subject"; //$NON-NLS-1$
	private static final String MESSAGE = "message"; //$NON-NLS-1$
	
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
		
		mailSender.sendMail(from, pw, name, to, subject, message);
	}

}
