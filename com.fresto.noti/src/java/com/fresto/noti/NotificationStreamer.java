package com.fresto.noti;

import org.apache.log4j.Logger;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class NotificationStreamer {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static int frontport = 7011;
	private static int backport = 7012;
	
	public void run() {
		//  Prepare our context and sockets
		Context context = ZMQ.context(1);

		//  This is where the weather server sits
		Socket frontend =  context.socket(ZMQ.SUB);
		frontend.connect("tcp://*:" + frontport);

		//  This is our public endpoint for subscribers
		Socket backend  = context.socket(ZMQ.PUSH);
		backend.bind("tcp://*:" + backport);

		logger.info("Starting Streamer with " + frontport + "/" + backport);
		
		//  Subscribe on everything
		frontend.subscribe("".getBytes());

		//  Run the proxy until the user interrupts us
		ZMQ.proxy (frontend, backend, null);

		frontend.close();
		backend.close();
		context.term();
	}
	
	public static void main (String[] args) {
		new NotificationStreamer().run();
	}

}
