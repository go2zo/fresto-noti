package com.fresto.noti.mail.core;

public class MailException extends MessageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 99793901530076920L;

	private String statusCode;	

	public MailException(String statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public MailException(String statusCode, String message) {
		super(message);
		this.statusCode = statusCode;
	}

	public MailException(String statusCode, String message, Throwable cause) {
		super(message, cause);
		this.statusCode = statusCode;
	}

	public MailException(String statusCode, Throwable cause) {
		super(cause);
		this.statusCode = statusCode;
	}
	
	public String getStatusCode() {
		return statusCode;
	}

}
