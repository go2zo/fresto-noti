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
	            System.err.println(e); // ������ �ִٸ� �޽��� ���
	            System.exit(1);
	        }
		 
		 //<Send a Mail>>
		 Properties gmailProps = new Properties();
		 gmailProps.put("mail.smtp.starttls.enable","true");
		 gmailProps.put("mail.smtp.host", "smtp.gmail.com");
		 gmailProps.put("mail.smtp.auth", "true");
		 gmailProps.put("mail.smtp.port", "465");
		 
		 String from = "jaeyoungjeon@gmail.com";
	     // �������� �����ּ�
	     String to = "jyeong.jeon@gmail.com";
	     // �޴��� �̸��� �ּҴ� �ݵ�� ","�� �������ش�. String to = "���� �̸��� �ּ�1,���� �̸��� �ּ�2";
	     String subject = "�̸��� �߼� �׽�Ʈ";
	     String content = "�ȳ��ϼ��� Java Email �߼� �׽�Ʈ�Դϴ�.";
	         
	     MailSender emailSender = new MailSender(gmailProps);
	     //EmailSender emailSender = new EmailSender(jamesProps);
	     emailSender.sendMail(from, to, subject, content);
		 
		 //</Send a Mail>
	     System.out.println("Mail Sent !");
	}

}
