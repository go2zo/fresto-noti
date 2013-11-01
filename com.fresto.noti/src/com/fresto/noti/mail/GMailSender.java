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
package com.fresto.noti.mail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class GMailSender {
	
	public void sendMail(String address, String password, String personal,
			String recipients, String subject, String content){

		try{
			InputStream inStream = new FileInputStream("gmail.properties");

			Properties props = new Properties();
			props.load(inStream);

			MyAuthenticator auth = new MyAuthenticator(address, password);

			Session mailSession	= Session.getDefaultInstance(props, auth);
			
			InternetAddress fromAddress = new InternetAddress(address,
					MimeUtility.encodeText(personal, "UTF-8", "B"));
			
			// recipients: ex) "aaa@abc.com, bbb.xyz.com, ..."
			String[] splits = recipients.split(",");
			if (splits.length == 0) {
				// wrong argument exception
			}
			InternetAddress[] toAddresses = new InternetAddress[splits.length];
			int newLength = 0;
			for (int i = 0; i < splits.length; i++) {
				String recipient = splits[i].trim();
				if ( validateAddress(recipient) ) {
					toAddresses[i] = new InternetAddress(recipient);
					newLength += 1;
				}
			}
			
			if (newLength != splits.length) {
				toAddresses = Arrays.copyOf(toAddresses, newLength);
			}
			
			sendMail(mailSession, fromAddress, toAddresses, new Date(), subject, content);

		} catch ( MessagingException ex ) {
			System.out.println("mail send error : " + ex.getMessage());
		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}
	
	private void sendMail(Session session, Address from, Address[] recipients,
			Date date, String subject, String text)
			throws MessagingException {
		Message msg = new MimeMessage(session);
		
		msg.setFrom(from);
		msg.setRecipients(Message.RecipientType.TO, recipients);
		
		msg.setSentDate(date);
		msg.setSubject(subject);
		msg.setText(text);
		
		Transport.send(msg);
	}
	
	/**
	 * validate mail address
	 * 
	 * @param address
	 * @return
	 */
	private boolean validateAddress(String address) {
		if (address.equals("")) {
			return false;
		}
		return true;
	}
}
