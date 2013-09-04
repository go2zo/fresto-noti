package OpenSourceAPM.esper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

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
	        	//bw.write(IP_Address + " " + UUID + " " + Long.toString(ResponseTime) + " " + URL + " " + s + "\n");
	        	bw.write(URL + " " + Long.toString(count) + " " + s + "\n");
	        	bw.close();
	        	fw.close();        	      			        	
	        }catch (IOException e) {
	            System.err.println(e); // 에러가 있다면 메시지 출력
	            System.exit(1);
	        }
	}

}
