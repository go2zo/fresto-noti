package OpenSourceAPM.esper;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class GMailSender {
	String host = "smtp.gmail.com";
	//String subject = "Event �߻� ����";
	String from = "noti.opensourceapm@gmail.com";
	String fromName = "OpensourceAPM";
	//String to = "jaeyoungjeon@gmail.com";
	//String content = "Event �� �߻��޽��ϴ�. �α׸� Ȯ�����ּ��� !";
	
	public void sendMail(String to, String subject, String content){
	
	try{
		Properties props = new Properties();
		
		props.put("mail.smtp.starttls.enable","true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        
        MyAuthenticator auth = new MyAuthenticator("noti.opensourceapm@gmail.com","zaq12wsx!");
        
        Session mailSession	= Session.getDefaultInstance(props, auth);
        
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//������ ��� ����
        InternetAddress[] address = {new InternetAddress(to)};
        
        msg.setRecipients(Message.RecipientType.TO, address);//�޴� �������
        
        msg.setSubject(subject);// ���� ����
        msg.setSentDate(new java.util.Date());// ������ ��¥ ����
//        msg.setContent(content,"text/html;charset=utf-8"); // ���� ���� (HTML ����)
        msg.setText(content);
        
        Transport.send(msg); // ���� ������
        
        System.out.println("���� �߼��� �Ϸ��Ͽ����ϴ�.");
    } catch ( MessagingException ex ) {
        System.out.println("mail send error : " + ex.getMessage());
    } catch ( Exception e ) {
        System.out.println("error : " + e.getMessage());
    }

	}
}



class MyAuthenticator extends Authenticator {
    private String id;
    private String pw;
    public MyAuthenticator(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(id, pw);
    }
}
