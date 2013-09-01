package OpenSourceAPM.esper;

import java.io.*;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class MyListener implements UpdateListener {
	public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        EventBean event = newEvents[0];
           
        //System.out.println(event.get("IP_Address"));
        try {
        	FileWriter fw = new FileWriter("AlarmLog.txt",true);
        	BufferedWriter bw = new BufferedWriter(fw);
        	bw.write((String) event.get("ipAddress") + ", " + (Long) event.get("responseTime") + ", "+ (String) event.get("uuid")+ "," + (String) event.get("url")+"\n");
        	bw.close();
        	fw.close(); 
        }catch (IOException e) {
            System.err.println(e); // 에러가 있다면 메시지 출력
            System.exit(1);
        }
        
       
    }

}
