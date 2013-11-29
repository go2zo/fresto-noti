package com.fresto.noti.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fresto.noti.mail.core.MailException;
import com.fresto.noti.mail.core.MailStatusCode;
import com.fresto.noti.mail.core.MessageException;
import com.fresto.noti.mail.service.MailSendManager;

public class SMTPMailSendManager implements MailSendManager {

	private Logger logger = LoggerFactory.getLogger(SMTPMailSendManager.class);

	private String protocol = "smtp";
	private String type = "text/html; charset=KSC5601";
	
	private String host;
	private int port;
	private String userName;
	private String password;
	private boolean starttlsEnable;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setStarttlsEnable(boolean starttlsEnable) {
		this.starttlsEnable = starttlsEnable;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void send(String toAddresses, String fromAddress, Date date, String subject, String content) throws MessageException {
		try {
			InternetAddress[] toList = InternetAddress.parse(toAddresses);
			InternetAddress from = new InternetAddress(fromAddress);
			send(toList, from, date, subject, content);
		} catch (AddressException e) {
			logger.warn(e.getMessage(), e);
		} 
	}
	
	@Override
	public void send(Address[] to, Address from, Date date, String subject, String content) throws MessageException {
		try {
			if (userName == null) {
				userName = ((InternetAddress) from).getAddress();
				if (userName == null) {
					userName = ""; //$NON-NLS-1$
				}
			}
			
			Properties props = getProperties();
			Authenticator authenticator = getAuthenticator();
			Session session = Session.getDefaultInstance(props, authenticator);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(from);
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSentDate(date);
			message.setSubject(subject);
			message.setContent(content, type);

			Transport.send(message);
			logger.info("메일 발송 성공 ");
		} catch (MessagingException e) {
			logger.warn("메일 발송 실패");
			throw new MailException(MailStatusCode.SEND_FAIL, "메일을 발송하는 중 에러가 발생했습니다.", e);
		}
	}
	
	/**
	 * @return properties
	 */
	protected Properties getProperties() {
		Properties props = new Properties();
		
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", String.valueOf(port));

		if (StringUtils.isNotBlank(userName)) {
			props.put("mail.smtp.auth", "true");
		}

		if (starttlsEnable) {
			props.put("mail.smtp.starttls.enable", Boolean.toString(starttlsEnable));   
		}
		
		return props;
	}
	
	/**
	 * @return smtp authenticator
	 */
	protected Authenticator getAuthenticator() {
		if (StringUtils.isNotBlank(userName)) {
			return new SMTPAuthenticator(userName, password);
		}
		return null;
	}
	
	/**
	 * 
	 */
	class SMTPAuthenticator extends Authenticator {
		PasswordAuthentication passwordAuthentication;
		
		SMTPAuthenticator(String userName, String password) {
			passwordAuthentication = new PasswordAuthentication(userName, password);
		}
		
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return passwordAuthentication;
		}
	}

}