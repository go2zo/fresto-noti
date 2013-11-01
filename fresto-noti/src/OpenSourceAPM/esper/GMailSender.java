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
package OpenSourceAPM.esper;

import java.io.File;
import java.io.FileInputStream;
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
	String from = "noti.opensourceapm@gmail.com";
	String fromName = "OpensourceAPM";
	
	public void sendMail(String to, String subject, String content){
	
	try{
		Properties props = new Properties();
		props.load(new FileInputStream(new File("gmail.properties")));
		
        MyAuthenticator auth = new MyAuthenticator(from,"zaq12wsx!");
        
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
