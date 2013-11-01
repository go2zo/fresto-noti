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

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

public class FrestoEventQueue extends Thread {

	private Logger logger = Logger.getLogger(getClass().getName());

	private Queue<FrestoEvent> queue = new ConcurrentLinkedQueue<>();
	private AtomicBoolean work = new AtomicBoolean(true);
	private ZMQ.Socket receiveSocket;

	public FrestoEventQueue() {
		
	}
	
	public FrestoEventQueue(ZMQ.Socket receiveSocket) {
		this.receiveSocket = receiveSocket;
	}

	@Override
	public void run() {
		logger.info("Starting...");

		if (receiveSocket == null) {
			logger.error("ReceiveSocket not set");
		}
		
		while(work.get()) {
			try {
				String topic = new String(receiveSocket.recv(0)); 
				byte[] eventBytes = receiveSocket.recv(0);
				FrestoEvent frestoEvent = new FrestoEvent(topic, eventBytes);
				queue.add(frestoEvent);
			} catch(ZMQException e) {
				if(e.getErrorCode() == ZMQ.Error.ETERM.getCode()){
					logger.info("Breaking...");
					break;
				}
			}
		}
		logger.info("Shutting down...");
	}

	public void setPullerSocket(ZMQ.Socket receiveSocket) {
		this.receiveSocket = receiveSocket;
	}
	
	public void stopWork() {
		this.work.set(false);
	}

	public int size() {
		return queue.size();
	}

	public FrestoEvent poll() {
		return queue.poll();
	}

	public Iterator<FrestoEvent> getIterator() {
		return queue.iterator();
	}

	public void remove(FrestoEvent event) {
		this.queue.remove(event);
	}
}
