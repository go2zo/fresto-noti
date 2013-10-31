package com.fresto.noti.subs;

import java.util.HashMap;
import java.util.Map;


public class SubscriberDescriptor {

	private Class<?> clazz;
	private String id;
	private Map<String, String> params;
	
	public SubscriberDescriptor(Class<?> clazz) {
		this(clazz, "");
	}
	
	public SubscriberDescriptor(Class<?> clazz, String id) {
		this.clazz = clazz;
		this.id = id;
		this.params = new HashMap<String, String>();
	}
	
	public Object getSubscriber() throws InstantiationException, IllegalAccessException {
		Object obj = clazz.newInstance();
		if (obj instanceof Subscriber) {
			((Subscriber)obj).setIdentifier(id);
			((Subscriber)obj).putParams(params);
		}
		return obj;
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
}
