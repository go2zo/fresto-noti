package com.fresto.noti;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

import fresto.command.CommandEvent;
import fresto.data.FrestoData;

public class NotificationWriter extends Thread {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationWriter.class);
	
	private static String ZMQ_URL = "tcp://*:7012";
	
	private static final String TARGET_MODULE = "noti";
	private static final String COMMAND_EXIT = "exit";
	
	private static final String TOPIC_COMMAND_EVENT = "CMD";
	private static final String TOPIC_NOTIFICATION = "NTF";
	
	private static boolean work = true;
	private static boolean sleepOn = false;
	private static long SLEEP_TIME = 100;
	
	private TDeserializer deserializer;
	
	public void run() {
		
		deserializer = new TDeserializer(new TBinaryProtocol.Factory());
		
		final ZMQ.Context context = ZMQ.context(1);
		final FrestoEventQueue frestoEventQueue = new FrestoEventQueue();
		
		final Thread writerThread = new Thread() {
			@Override
			public void run() {
				ZMQ.Socket puller = context.socket(ZMQ.SUB);
				puller.connect(ZMQ_URL);
				puller.subscribe("".getBytes());
				
				frestoEventQueue.setPullerSocket(puller);
				frestoEventQueue.start();
				
				try {
					while(work) {

						// To add sufficient events to the queue
						if (sleepOn) {
							Thread.sleep(SLEEP_TIME);
						}

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
//							logger.info(queueSize + " events.");
						}

					}
				} catch (TException e) {
					logger.warn("Exception occurred: " + e.getMessage());
				} catch (InterruptedException e) {
				}
				
				puller.close();
				context.term();
			}
		};
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("Interrupt received, killing server");
				// To break while clause
				frestoEventQueue.stopWork();
				work = false;

				try {
					frestoEventQueue.join();
					writerThread.join();

				} catch (InterruptedException e) {
				}
			}
		});
		
		writerThread.start();
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
			
			NotificationService.getInstance().sendEvent(frestoData);
		} else {
			logger.warn("Event topic: " + topic + " not recognized. Possible valures: " + TOPIC_NOTIFICATION); 
		}
	}
	
}
