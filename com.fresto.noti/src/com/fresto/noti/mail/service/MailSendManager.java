package com.fresto.noti.mail.service;

import java.util.Date;

import javax.mail.Address;

import com.fresto.noti.mail.core.MessageException;

public interface MailSendManager {

	void send(String toAddress, String fromAddress, Date date, String subject, String content) throws MessageException;
	void send(Address[] to, Address from, Date date, String subject, String content) throws MessageException;
	
}
