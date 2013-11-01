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
			System.err.println(e); // ������ �ִٸ� �޽��� ���
			System.exit(1);
		}

		String to = "jyeong.jeon@gmail.com";
		//String to = "hunjae.lee@gmail.com";
		// �޴��� �̸��� �ּҴ� �ݵ�� ","�� �������ش�. String to = "���� �̸��� �ּ�1,���� �̸��� �ּ�2";
		String subject = "Alarm 1 �̺�Ʈ�� �߻��Ͽ����ϴ� !";
		String content = "Alarm 1 �̺�Ʈ�� �߻��Ͽ����ϴ�.!" + "\n" + "�ֱ� 30�ʰ� ��� 100 ms �̻��� ����ð��� �ҿ�� ȣ���� �߻��Ǿ����ϴ�." + "\n" + "�α׸� Ȯ���ϼ���.";

		GMailSender mailSender = new GMailSender();
		mailSender.sendMail(to, subject, content);



	}

}
