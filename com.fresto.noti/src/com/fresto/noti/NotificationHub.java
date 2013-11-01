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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

public class NotificationHub extends Thread {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationHub.class);
	
	private static int frontport = 7010;
	private static int backport = 7011;
	
	public void run() {
		//  Prepare our context and sockets
		ZMQ.Context context = ZMQ.context(1);
			
		//  This is where the weather server sits
		ZMQ.Socket frontend = context.socket(ZMQ.SUB);
		frontend.connect("tcp://*:" + frontport);

		//  This is our public endpoint for subscribers
		ZMQ.Socket backend  = context.socket(ZMQ.PUB);
		backend.bind("tcp://*:" + backport);

		logger.info("Starting Forwarder with " + frontport + "/" + backport);

		//  Subscribe on everything
		frontend.subscribe("".getBytes());
		
		//  Run the proxy until the user interrupts us
		ZMQ.proxy (frontend, backend, null);

		frontend.close();
		backend.close();
	}
	
}
