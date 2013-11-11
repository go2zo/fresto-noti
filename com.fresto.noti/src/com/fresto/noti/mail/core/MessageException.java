package com.fresto.noti.mail.core;

public class MessageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2695246781223511959L;

	public MessageException() {
		super();
	}

	public MessageException(String arg0) {
		super(arg0);
	}

	public MessageException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MessageException(Throwable arg0) {
		super(arg0);
	}

}
