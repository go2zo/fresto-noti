package com.fresto.noti.extensions;

public class ExtensionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2922706999901357444L;

	public ExtensionException() {
		super();
	}
	
	public ExtensionException(String message) {
		super(message);
	}
	
	public ExtensionException(Throwable cause) {
		super(cause);
	}
}
