/**************************************************************************************
 * Copyright 2013 TheSystemIdeas, Inc and Contributors. All rights reserved.          *
 *                                                                                    *
 *     https://github.com/owlab/fresto                                                *
 *                                                                                    *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * This file is licensed under the Apache License, Version 2.0 (the "License");       *
 * you may not use this file except in compliance with the License.                   *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 * 
 **************************************************************************************/
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
