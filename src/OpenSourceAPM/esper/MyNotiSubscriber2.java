package OpenSourceAPM.esper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import java.util.Properties;

public class MyNotiSubscriber2 {
	//public void update(String IP_Address, String UUID, long ResponseTime, String URL){
	  public void update(String URL, long count){	
		 try {	
			 
			 	//<Date-Time>
			 	Calendar cal = Calendar.getInstance();
			  	String s = String.format("%04d-%02d-%02d %02d:%02d:%02d",cal.get(Calendar.YEAR),(cal.get(Calendar.MONTH) + 1),cal.get(Calendar.DAY_OF_MONTH),
			                             cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
			 	//</Date-Time>
			 
	        	FileWriter fw = new FileWriter("AlarmLog2.txt",true);
	        	BufferedWriter bw = new BufferedWriter(fw);
	        	bw.write(URL + " " + Long.toString(count) + " " + s + "\n");
	        	bw.close();
	        	fw.close();        	      			        	
	        }catch (IOException e) {
	            System.err.println(e); // 에러가 있다면 메시지 출력
	            System.exit(1);
	        }
		 
		 //<Send a Mail>>
		 Properties gmailProps = new Properties();
		 gmailProps.put("mail.smtp.starttls.enable","true");
		 gmailProps.put("mail.smtp.host", "smtp.gmail.com");
		 gmailProps.put("mail.smtp.auth", "true");
		 gmailProps.put("mail.smtp.port", "465");
		 
		 String from = "jaeyoungjeon@gmail.com";
	     // 보내는이 메일주소
	     String to = "jyeong.jeon@gmail.com";
	     // 받는이 이메일 주소는 반드시 ","로 구분해준다. String to = "받을 이메일 주소1,받을 이메일 주소2";
	     String subject = "이메일 발송 테스트";
	     String content = "안녕하세요 Java Email 발송 테스트입니다.";
	         
	     MailSender emailSender = new MailSender(gmailProps);
	     //EmailSender emailSender = new EmailSender(jamesProps);
	     emailSender.sendMail(from, to, subject, content);
		 
		 //</Send a Mail>
	     System.out.println("Mail Sent !");
	}

}
