package test;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.zeromq.ZMQ;

import fresto.command.CommandEvent;

public class SubmitCommand {
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("Argements: <target module> <command>");
			System.exit(0);
		}
		TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());

		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket publisher = context.socket(ZMQ.PUB);
		publisher.bind("tcp://*:7010");
		String commandTopic = "CMD";
		
		while(true) {
		CommandEvent event = new CommandEvent();
		event.target_module = args[0];
		event.command = args[1];
		try {
			byte[] eventBytes = serializer.serialize(event);
			publisher.send(commandTopic.getBytes(), ZMQ.SNDMORE);
			publisher.send(eventBytes, 0);
			
			System.out.println("send: [" + commandTopic + ", " + event + "]");
			
			Thread.sleep(1000);
		} catch(TException te) {
			te.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}

//		publisher.close();
//		context.term();
	}
	
}
