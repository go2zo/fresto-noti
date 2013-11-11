package test;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.junit.Test;
import org.zeromq.ZMQ;

import fresto.data.ClientID;
import fresto.data.DataUnit;
import fresto.data.FrestoData;
import fresto.data.Pedigree;
import fresto.data.ResourceID;
import fresto.data.ResponseEdge;

public class NotificationServiceTest {

	@Test
	public void testFrestoDataSend() {
		TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());

		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket publisher = context.socket(ZMQ.PUB);
		publisher.bind("tcp://*:7010");
		String notiTopic = "NTF";

		FrestoData data = new FrestoData();
		Pedigree pedigree = new Pedigree(500);
		data.setPedigree(pedigree);

		ResourceID.url("http://www.aaa.com");
		ResponseEdge edge = new ResponseEdge(
				ResourceID.url("http://www.aaa.com"),
				ClientID.clientIp("127.0.0.1"),
				200, 100, 1234567890, "A123B");
		DataUnit dataUnit = DataUnit.responseEdge(edge);
		
		data.setDataUnit(dataUnit);

		int i = 0;
		while(true) {
			try {
				byte[] eventBytes = serializer.serialize(data);
				publisher.send(notiTopic.getBytes(), ZMQ.SNDMORE);
				publisher.send(eventBytes, 0);

				System.out.println("send: [" + notiTopic + ", " + data + "]");
				if (i++ == 1) {
					break;
				}
				Thread.sleep(1000);
			} catch(TException te) {
				te.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
