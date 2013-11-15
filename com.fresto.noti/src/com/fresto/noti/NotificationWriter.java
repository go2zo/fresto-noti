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
package com.fresto.noti;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

import com.fresto.core.Topics;

import fresto.command.CommandEvent;
import fresto.data.DataUnit;
import fresto.data.FrestoData;

public class NotificationWriter extends Thread implements Topics {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationWriter.class);
	
	private static String ZMQ_URL = "tcp://localhost:7001";
	
	private static final String TARGET_MODULE = "noti";
	private static final String COMMAND_EXIT = "exit";
	
	private static boolean work = true;
	private static boolean sleepOn = false;
	private static long SLEEP_TIME = 100;
	
	private TDeserializer deserializer;
	
	private String address; 
	
	public NotificationWriter() {
		this(ZMQ_URL);
	}
	
	public NotificationWriter(String addr) {
		this.address = addr;
	}
	
	public void run() {
		
		deserializer = new TDeserializer(new TBinaryProtocol.Factory());
		
		final ZMQ.Context context = ZMQ.context(1);
		final FrestoEventQueue frestoEventQueue = new FrestoEventQueue();
		
		final Thread writerThread = new Thread() {
			@Override
			public void run() {
				ZMQ.Socket puller = context.socket(ZMQ.SUB);
				puller.connect(address);
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
							logger.debug(queueSize + " events processed.");
						} else {
							logger.debug(queueSize + " events.");
						}

					}
				} catch (TException e) {
					logger.warn("Exception occurred: " + e.getMessage(), e);
				} catch (InterruptedException e) {
				}
				
				puller.close();
				context.term();
			}
		};
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.info("Interrupt received, killing server");
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
				logger.debug("Perform command: " + event.command); 
			} else {
				logger.warn("Unsupported command: " + event.command);
			}
		}
	}
	
	private void writeNotificationData(String topic, byte[] eventBytes) throws TException {
		if (	/*TOPIC_REQUEST.equals(topic) ||*/
				TOPIC_RESPONSE.equals(topic) ||
				/*TOPIC_ENTRY_CALL.equals(topic) ||*/
				TOPIC_ENTRY_RETURN.equals(topic) ||
				/*TOPIC_OPERATION_CALL.equals(topic) ||*/
				TOPIC_OPERATION_RETURN.equals(topic) ||
				/*TOPIC_SQL_CALL.equals(topic) ||*/
				TOPIC_SQL_RETURN.equals(topic)) {
			FrestoData frestoData = new FrestoData();
			deserializer.deserialize(frestoData, eventBytes);
//			NotificationService.getInstance().sendEvent(frestoData);
			
			DataUnit dataUnit = frestoData.getDataUnit();
			Object fieldValue = dataUnit.getFieldValue();
			NotificationService.getInstance().sendEvent(fieldValue);
			
		} else {
//			logger.warn("Event topic: " + topic + " not recognized."); 
		}
	}
	
	
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				URI uri = new URI(args[0]);
				NotificationService.getInstance();
				new NotificationWriter(uri.toString()).start();
			} catch (URISyntaxException e) {
				logger.error("Wrong parameter : " + args[0]);
			}
		} else {
			new NotificationWriter().start();
		}
	}
}
