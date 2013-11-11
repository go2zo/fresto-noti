package com.fresto.noti.subs;

public class SubscriberFactory {

	private static SubscriberFactory instance;
	
	public static SubscriberFactory getInstance() {
		if (instance == null) {
			instance = new SubscriberFactory();
		}
		return instance;
	}
	
	public Subscriber createSubscriber(String type) {
		if (Subscriber.TYPE_MAIL.equalsIgnoreCase(type)) {
			return new MailSubscriber();
		}
		if (Subscriber.TYPE_JS.equalsIgnoreCase(type)) {
			return new JavascriptSubscriber();
		}
		return null;
	}
}
