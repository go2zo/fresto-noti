package OpenSourceAPM.esper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Calendar;

public class MyNotiSubscriber1 {


	public void update(double ResponseTime, String URL){	
		try {	

			//<Date-Time>
			Calendar cal = Calendar.getInstance();
			String s = String.format("%04d-%02d-%02d %02d:%02d:%02d",cal.get(Calendar.YEAR),(cal.get(Calendar.MONTH) + 1),cal.get(Calendar.DAY_OF_MONTH),
					cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));
			//</Date-Time>

			FileWriter fw = new FileWriter("AlarmLog1.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			//bw.write(IP_Address + " " + UUID + " " + Long.toString(ResponseTime) + " " + URL + " " + s + "\n");
			bw.write(Double.toString(ResponseTime) + " " + URL + " " + s + "\n");
			bw.close();
			fw.close();        	      			        	
		}catch (IOException e) {
			System.err.println(e); // 에러가 있다면 메시지 출력
			System.exit(1);
		}

		String to = "jyeong.jeon@gmail.com";
		//String to = "hunjae.lee@gmail.com";
		// 받는이 이메일 주소는 반드시 ","로 구분해준다. String to = "받을 이메일 주소1,받을 이메일 주소2";
		String subject = "Alarm 1 이벤트가 발생하였습니다 !";
		String content = "Alarm 1 이벤트가 발생하였습니다.!" + "\n" + "최근 30초간 평균 100 ms 이상의 응답시간이 소요된 호출이 발생되었습니다." + "\n" + "로그를 확인하세요.";

		GMailSender mailSender = new GMailSender();
		mailSender.sendMail(to, subject, content);



	}

}
