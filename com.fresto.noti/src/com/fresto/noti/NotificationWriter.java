package com.fresto.noti;

import org.apache.log4j.Logger;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.zeromq.ZMQ;

import fresto.command.CommandEvent;
import fresto.data.FrestoData;

public class NotificationWriter {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static String ZMQ_URL = "tcp://127.0.0.1:5000";
	
	private static final String TARGET_MODULE = "noti";
	private static final String COMMAND_EXIT = "exit";
	
	private static final String TOPIC_COMMAND_EVENT = "CMD";
	private static final String TOPIC_NOTIFICATION = "NTF";
	
	private boolean work = true;
	private long SLEEP_TIME = 100;
	
	private TDeserializer deserializer;
	
	public void run() throws Exception {
		
		deserializer = new TDeserializer(new TBinaryProtocol.Factory());
		
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket puller = context.socket(ZMQ.SUB);
		puller.connect(ZMQ_URL);
		
		FrestoEventQueue frestoEventQueue = new FrestoEventQueue(puller);
		frestoEventQueue.start();
		
		while(work) {

			// To add sufficient events to the queue
			Thread.sleep(SLEEP_TIME);

			int queueSize = frestoEventQueue.size();
			
			if(queueSize > 0) {
				for(int i = 0; i < queueSize; i++) {
					FrestoEvent frestoEvent = frestoEventQueue.poll(); 
					if(TOPIC_COMMAND_EVENT.equals(frestoEvent.getTopic())) {
						handleCommand(frestoEvent.getTopic(), frestoEvent.getEventBytes());
					} else {
						writeNotificationData(frestoEvent.getTopic(), frestoEvent.getEventBytes());
					}
				}
				logger.info(queueSize + " events processed.");
			} else {
				logger.info(queueSize + " events.");

			}

		}

		puller.close();
		context.term();
	}
	
	private void handleCommand(String topic, byte[] eventBytes) throws TException {
		
		CommandEvent event = new CommandEvent();
		deserializer.deserialize(event, eventBytes);
		if (event.target_module.equalsIgnoreCase(TARGET_MODULE)) {
			if (event.command.equalsIgnoreCase(COMMAND_EXIT)) {
				work = false;
				logger.info("Perform command: " + event.command); 
			} else {
				logger.warn("Unsupported command: " + event.command);
			}
		}
	}
	
	private void writeNotificationData(String topic, byte[] eventBytes) throws TException {
		if(TOPIC_NOTIFICATION.equals(topic)) {
			FrestoData frestoData = new FrestoData();
			deserializer.deserialize(frestoData, eventBytes);
			
			NotificationManager.getInstance().sendEvent(frestoData);
		} else {
			logger.warn("Event topic: " + topic + " not recognized. Possible valures: " + TOPIC_NOTIFICATION); 
		}
	}
	
	public static void main(String[] args) {
		try {
			new NotificationWriter().run();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}

}
