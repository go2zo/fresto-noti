package com.fresto.noti.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
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
			URL url = ClassLoader.getSystemResource("gmail.config.properties");
			File file = new File(url.getFile());
			InputStream inStream = new FileInputStream(file);

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
			System.out.println("error : " + e.getMessage());
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
