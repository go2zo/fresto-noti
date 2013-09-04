package OpenSourceAPM.esper;

import java.io.*;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
        //EventBean event[] = newEvents;  
           
        //System.out.println(event.get("IP_Address"));
        try {
        	
        	FileWriter fw = new FileWriter("AlarmLog.txt",true);
        	BufferedWriter bw = new BufferedWriter(fw);
        	//bw.write((String) event[0].get("ipAddress") + ", " + (Long) event[0].get("responseTime") + ", "+ (String) event[0].get("uuid")+ "," + (String) event[0].get("url")+"\n");
        	//bw.write((String) event[1].get("ipAddress") + ", " + (Long) event[1].get("responseTime") + ", "+ (String) event[1].get("uuid")+ "," + (String) event[1].get("url")+"\n");
        	bw.write((String) event.get("ipAddress") + ", " + (Long) event.get("responseTime") + ", "+ (String) event.get("uuid")+ "," + (String) event.get("url")+"\n");
        	bw.close();
        	fw.close();
        	      	
        	
        }catch (IOException e) {
            System.err.println(e); // ������ �ִٸ� �޽��� ���
            System.exit(1);
        }
        
       
    }

}
