package OpenSourceAPM.esper;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;


public class MailSender {
	private Properties props;
	
	public MailSender(Properties props){
		this.props = props;
	}
	
	public void sendMail(String from, String to, String subject, String content){
		
		EmailAuthenticator authenticator = new EmailAuthenticator();
		
		Session session = Session.getInstance(props, authenticator);
		
		try{
			Message msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(from));
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setContent(content, "text/html; charset=UTF-8");
            msg.setSentDate(new Date());
            Transport.send(msg);			
		} catch (MessagingException e) {
             e.printStackTrace();
         }	
	}
}

class EmailAuthenticator extends Authenticator {
	private String id;
	private String pw;
	
	public EmailAuthenticator(){
		this.id = "jaeyoungjeon@gmail.com";
		this.pw = "cheon9bo";
	}
	
	public EmailAuthenticator(String id, String pw){
		this.id = id;
		this.pw = pw;
	}
	
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(id, pw);
	}
}
