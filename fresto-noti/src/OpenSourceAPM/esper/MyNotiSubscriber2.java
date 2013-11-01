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
	            System.err.println(e); // 에러가 있다면 메시지 출력
	            System.exit(1);
	        }
		 
		
	     //String to = "jyeong.jeon@gmail.com";
		 String to = "hunjae.lee@gmail.com";
	     // 받는이 이메일 주소는 반드시 ","로 구분해준다. String to = "받을 이메일 주소1,받을 이메일 주소2";
	     String subject = "Alarm 2 이벤트가 발생하였습니다 !";
	     String content = "Alarm 2 이벤트가 발생하였습니다.!" + "\n" + "10초간 5회 이상의 호출이 발생되었습니다." + "\n" + "로그를 확인하세요.";
	         
	     GMailSender mailSender = new GMailSender();
	     mailSender.sendMail(to, subject, content);
	
	}

}
