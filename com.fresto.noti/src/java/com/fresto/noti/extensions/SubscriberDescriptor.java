package com.fresto.noti.extensions;

public class SubscriberDescriptor {

	private String epl;
	private String contextId;
	private Class<?> contextClass;
	private Object instance;

	public String getContextId() {
		return contextId;
	}
	
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	
	public String getEpl() {
		return epl;
	}
	
	public void setEpl(String epl) {
		this.epl = epl;
	}
	
	public void setContextClass(Class<?> contextClass) {
		this.contextClass = contextClass;
	}
	
	public Object getSubscriber() throws RuntimeException {
		if (instance == null) {
			instance = createSubscriber();
		}
		return instance;
	}
	
	private Object createSubscriber() throws RuntimeException {
		try {
			Object subscriber = contextClass.newInstance();
			return subscriber;
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
}
