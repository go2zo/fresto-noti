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
package com.fresto.noti.subs;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberDescriptor {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String type;
	private Class<?> clazz;
	private String id;
	private Map<String, String> params;
	
	public SubscriberDescriptor() {
		
	}
	
	public SubscriberDescriptor(String type) {
		this.type = type;
		this.params = new HashMap<String, String>();
	}
	
	public Object getSubscriber() throws InstantiationException, IllegalAccessException {
		Subscriber subscriber = SubscriberFactory.getInstance().createSubscriber(type);
		
		if (subscriber == null && clazz != null) {
			Object obj = clazz.newInstance();
			if (obj instanceof Subscriber) {
				subscriber = (Subscriber) obj;
			}
		}
		
		if (subscriber != null) {
			subscriber.setIdentifier(id);
			subscriber.putParams(params);
		}
		
		logger.debug(subscriber.toString());
		
		return subscriber;
	}
	
	public void setSubscriberClass(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public void setIdentifier(String id) {
		this.id = id;
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getSimpleName()).append("{");
		sb.append("class=").append(clazz);
		sb.append(", id=").append(id);
		sb.append(", params=").append(params.toString());
		sb.append("}");
		return sb.toString();
	}
}
