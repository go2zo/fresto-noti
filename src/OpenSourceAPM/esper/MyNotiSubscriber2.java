package OpenSourceAPM.esper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import java.util.Properties;

public class MyNotiSubscriber2 {
	
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
		 
		
	     //String to = "jyeong.jeon@gmail.com";
		 String to = "hunjae.lee@gmail.com";
	     // �޴��� �̸��� �ּҴ� �ݵ�� ","�� �������ش�. String to = "���� �̸��� �ּ�1,���� �̸��� �ּ�2";
	     String subject = "Alarm 2 �̺�Ʈ�� �߻��Ͽ����ϴ� !";
	     String content = "Alarm 2 �̺�Ʈ�� �߻��Ͽ����ϴ�.!" + "\n" + "10�ʰ� 5ȸ �̻��� ȣ���� �߻��Ǿ����ϴ�." + "\n" + "�α׸� Ȯ���ϼ���.";
	         
	     GMailSender mailSender = new GMailSender();
	     mailSender.sendMail(to, subject, content);
	
	}

}
