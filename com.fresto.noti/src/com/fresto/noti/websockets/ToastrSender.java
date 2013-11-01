package com.fresto.noti.websockets;

import java.text.MessageFormat;

import org.zeromq.ZMQ;

public class ToastrSender {
	
	private enum ToastrType {
		success, info, warning, error;
	}

	private String addr;
	
	public ToastrSender(String dest) {
		this.addr = dest;
	}
	
	public void success(String msg) {
		send(ToastrType.success, msg);
	}
	
	public void info(String msg) {
		send(ToastrType.info, msg);
	}
	
	public void warning(String msg) {
		send(ToastrType.warning, msg);
	}
	
	public void error(String msg) {
		send(ToastrType.error, msg);
	}
	
	private void send(final ToastrType type, final String msg) {
		Thread sendThread = new Thread() {
			@Override
			public void run() {
				String sendMsg = MessageFormat.format("toastr[{0}]({1})", type.name(), msg); //$NON-NLS-1$
				new WebsocketsClient("myapp", 18001).start();
				
			}
		};
	}
	
}
