package com.fresto.noti;

import java.util.ArrayList;
import java.util.List;

public class Notification {
	private String epl;
	private List<String> subscribers;
	
	public Notification() {
		this("");
	}
	
	public Notification(String epl) {
		this.epl = epl;
		this.subscribers = new ArrayList<>();
	}
	
	public String getEpl() {
		return epl;
	}
	
	public void setEpl(String epl) {
		this.epl = epl;
	}
	
	public List<String> getSubscribers() {
		return subscribers;
	}
	
	public void setSubscribers(List<String> subscribers) {
		this.subscribers = subscribers;
	}
	
	public void addSubscriber(String subscriber) {
		subscribers.add(subscriber);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName()).append("{");
		sb.append("epl=").append(epl);
		sb.append("subscribers=").append(subscribers.toString());
		sb.append("}");
		return sb.toString();
	}
}
