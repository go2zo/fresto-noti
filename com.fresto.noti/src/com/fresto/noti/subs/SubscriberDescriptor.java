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
