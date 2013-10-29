package com.fresto.noti;

public class FrestoEvent {
	
	private String topic;
	private byte[] eventBytes;
	
	public FrestoEvent(String topic, byte[] eventBytes){
		this.topic = topic;
		this.eventBytes = eventBytes;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public byte[] getEventBytes() {
		return eventBytes;
	}

	public void setEventBytes(byte[] eventBytes) {
		this.eventBytes = eventBytes;
	}
}
