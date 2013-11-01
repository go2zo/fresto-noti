package test;

import org.junit.Test;

import com.fresto.noti.mail.GMailSender;

public class GMailSenderTest {

	@Test
	public void testGMailSender() {
		GMailSender mailSender = new GMailSender();
		
		String from = "noti.opensourceapm@gmail.com";
		String name = "OpensourceAPM";
		String pw = "zaq12wsx!";
		String to = "go2zo@apexsoft.co.kr";
		String subject = "알람1이 발생하였습니다.";
		String message = "알람1이 발생하였다고합니다.";
		
		mailSender.sendMail(from, pw, name, to, subject, message);
	}
}
