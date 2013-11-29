package com.fresto.noti.mail.service;

import java.util.List;

import com.fresto.noti.mail.domain.Mail;

public interface MailManager {

	List<Mail> getMailList(String daemonName);
	int updateMail(Mail mail);
	
}
