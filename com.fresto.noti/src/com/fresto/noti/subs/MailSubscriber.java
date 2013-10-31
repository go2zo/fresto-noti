package com.fresto.noti.subs;

public class MailSubscriber {
	
	public void update(Object[] row) {
		System.out.println("MailSubscriber...");
		for (Object obj : row) {
			System.out.println(obj);
		}
	}
}
