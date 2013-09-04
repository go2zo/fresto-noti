package OpenSourceAPM.esper;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.zeromq.ZMQ;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class EsperEngine extends Thread {
	public static void main(String[] args) {
		//<Setting a configuration>
		Configuration config = new Configuration();
		config.addEventTypeAutoName("OpenSourceAPM.esper");
		//</Setting a configuration>

		//<Creating a Statement>
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		//String epl = "SELECT ipAddress, uuid, avg(responseTime), url from PageCallEvent.win:time_batch (5 sec) GROUP BY url HAVING avg(responseTime)>1  ";
		//String epl = "SELECT ipAddress, uuid, responseTime, url from PageCallEvent.win:time_batch (5 sec) GROUP BY url";
		String epl1 = "SELECT avg(responseTime), url from PageCallEvent.win:time_batch (30 sec) GROUP BY url HAVING avg(responseTime) > 100";
		EPStatement statement1 = epService.getEPAdministrator().createEPL(epl1);
		
		String epl2 = "SELECT url, count(*) from PageCallEvent.win:time_batch (10 sec) GROUP BY url HAVING count(*) > 5";
		EPStatement statement2 = epService.getEPAdministrator().createEPL(epl2);
		

		//<Adding a Listener>
		//MyListener listener = new MyListener();
		//statement.addListener(listener);
		//</Adding a Listener>
		
		//<Set Subscriber>
		MyNotiSubscriber1 notiSubscriber1 = new MyNotiSubscriber1();
		statement1.setSubscriber(notiSubscriber1);
		
		MyNotiSubscriber2 notiSubscriber2 = new MyNotiSubscriber2();
		statement2.setSubscriber(notiSubscriber2);
		
		
		
		//</Adding a Subscriber>
		
		
		//HJ
		TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());  

		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		subscriber.connect("tcp://fresto1.owlab.com:7001");
		//subscriber.subscribe("A".getBytes());
		subscriber.subscribe("U".getBytes());

		while(true) {
			System.out.println("Waiting...");
			String envelope = new String(subscriber.recv(0));
			byte[] messageBytes = subscriber.recv(0);
			try {
				UIEvent event = new UIEvent();
				deserializer.deserialize(event, messageBytes);
				System.out.println("Message Envelope: " + envelope);
				System.out.println("Event.stage : " + event.getStage());
				System.out.println("Event.clientId : " + event.getClientId());
				System.out.println("Event.currentPage : " + event.getCurrentPlace());
				System.out.println("Event.uuid : " + event.getUuid());
				System.out.println("Event.url : " + event.getUrl());
				System.out.println("Event.timestamp : " + event.getTimestamp());
				System.out.println("Event.elaspedTime : " + event.getElapsedTime());

				//PageCallEvent event1 = new PageCallEvent(event.getClientId(),event.getUuid(),event.getElapsedTime(),event.getUrl());
				//epService.getEPRuntime().sendEvent(event1);
				
				//
				if(event.getStage().equals("afterCall")){
					PageCallEvent event1 = new PageCallEvent(event.getClientId(),event.getUuid(),event.getElapsedTime(),event.getUrl());
					epService.getEPRuntime().sendEvent(event1);
				}
				


			} catch(TException te) {
				te.printStackTrace();
			}
		}

	}

}
