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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.fresto.noti.subs.Subscriber;
import com.fresto.noti.subs.SubscriberDescriptor;

public class NotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	private static NotificationService instance;
	
	/**
	 * @return singleton object
	 */
	public static NotificationService getInstance() {
		if (instance == null) {
			instance = new NotificationService();
		}
		return instance;
	}
	
	// property keys
	private static final String NOTIFICATIONS = "notifications"; //$NON-NLS-1$
	private static final String NOTIFICATION_PREFIX = "notification."; //$NON-NLS-1$
	private static final String NOTIFICATION_EPL = NOTIFICATION_PREFIX + "{0}.epl"; //$NON-NLS-1$
	private static final String NOTIFICATION_SUBSCRIBERS = NOTIFICATION_PREFIX + "{0}.subscribers"; //$NON-NLS-1$
	private static final String NOTIFICATION_SUBJECT = NOTIFICATION_PREFIX + "{0}.subject"; //$NON-NLS-1$
	private static final String NOTIFICATION_SUBJECT_SUB = NOTIFICATION_PREFIX + "{0}.subject.{1}"; //$NON-NLS-1$
	private static final String NOTIFICATION_CONTENT = NOTIFICATION_PREFIX + "{0}.content"; //$NON-NLS-1$
	private static final String NOTIFICATION_CONTENT_SUB = NOTIFICATION_PREFIX + "{0}.content.{1}"; //$NON-NLS-1$
	
	private static final String SUBSCRIBER_PREFIX = "subscriber."; //$NON-NLS-1$
	private static final String SUBSCRIBER = SUBSCRIBER_PREFIX + "{0}"; //$NON-NLS-1$
	private static final String SUBSCRIBER_TYPE = SUBSCRIBER_PREFIX + "{0}.type"; //$NON-NLS-1$
	private static final String SUBSCRIBER_CLASS = SUBSCRIBER_PREFIX + "{0}.class"; //$NON-NLS-1$
	
	private EPServiceProvider epService;
	
	private Map<String, List<String>> subscriberMap;
	private Map<String, SubscriberDescriptor> subscriberDescriptors;
	
	private NotificationService() {
		init();
		start();
	}
	
	/**
	 * 'notification.properties'을 파싱하여 SubscriberDescriptor 생성
	 */
	private void init() {
		subscriberMap = new HashMap<>();
		subscriberDescriptors = new HashMap<>();

		PropertiesConfiguration configuration = null;
		try {
			configuration = new KRPropertiesConfiguration("notification.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		// notifications=noti1, noti2, ...
		String[] notifications = configuration.getStringArray(NOTIFICATIONS);
		for (String notification : notifications) {
			// notification.noti1.epl
			String epl = configuration.getString( MessageFormat.format(NOTIFICATION_EPL, notification) );
			// notification.noti1.subscribers=mail1, js1, ...
			String[] subscribers = configuration.getStringArray( MessageFormat.format(NOTIFICATION_SUBSCRIBERS, notification) );
			// notification.noti1.subject
			String subject = configuration.getString( MessageFormat.format(NOTIFICATION_SUBJECT, notification) );
			// notification.noti1.content
			String content = configuration.getString( MessageFormat.format(NOTIFICATION_CONTENT, notification) );
			
			List<String> subList = new ArrayList<>();
			for (String subscriber : subscribers) {
				// subscriber.mail1.type=[mail | js | user]
				String type = configuration.getString( MessageFormat.format(SUBSCRIBER_TYPE, subscriber) );
				SubscriberDescriptor descriptor = new SubscriberDescriptor(type);
				
				if (Subscriber.TYPE_MAIL.equalsIgnoreCase(type) == false &&
						Subscriber.TYPE_JS.equals(type) == false) {
					// subscriber.user1.class
					String className = configuration.getString( MessageFormat.format(SUBSCRIBER_CLASS, subscriber) );
					if (className != null && !className.equals("")) {
						try {
							Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
							descriptor.setSubscriberClass(clazz);
						} catch (ClassNotFoundException e) {
							logger.warn(className + " cannot found.");
						}
					}
				}
				
				subList.add(subscriber);

				// notification.noti1.subject.mail1
				String subjectSubKey = MessageFormat.format(NOTIFICATION_SUBJECT_SUB, notification, subscriber);
				if (configuration.containsKey(subjectSubKey)) {
					subject = configuration.getString(subjectSubKey);
				}
				// notification.noti1.content.mail1
				String contentSubKey = MessageFormat.format(NOTIFICATION_CONTENT_SUB, notification, subscriber);
				if (configuration.containsKey(contentSubKey)) {
					content = configuration.getString(contentSubKey);
				}
				
				descriptor.addParam(Subscriber.SUBJECT, subject);
				descriptor.addParam(Subscriber.CONTENT, content);

				String subscriberPrefix = MessageFormat.format(SUBSCRIBER, subscriber);
				Iterator<String> keys = configuration.getKeys(subscriberPrefix);
				while (keys.hasNext()) {
					String key = keys.next();
					String values[] = configuration.getStringArray(key);

					String val = "";
					if (values.length == 1) {
						val = values[0];
					} else if (values.length > 1) {
						for (String value : values) {
							val += (value + ",");
						}
						val = val.substring(0, val.length() - 1);
					}
					// remove subscriber prefix
					key = key.substring(subscriberPrefix.length() + 1);
					descriptor.addParam(key, val);
					
					logger.debug(key + " = " + val);
				}

				// ???
				descriptor.setIdentifier(subscriber);
				
				subscriberDescriptors.put(subscriber, descriptor);
			}
			
			subscriberMap.put(epl, subList);
		}
	}
	
	/**
	 * SubscriberDescriptor를 통해 Subscriber를 생성하여 ESPER에 등록
	 */
	public void start() {
		Configuration config = new Configuration();
		config.addEventTypeAutoName("fresto.data");
		
		epService = EPServiceProviderManager.getDefaultProvider(config);
		EPAdministrator epAdmin = epService.getEPAdministrator();

		Iterator<String> iter = subscriberMap.keySet().iterator();
		while (iter.hasNext()) {
			String epl = iter.next();
			List<String> subNames = subscriberMap.get(epl);
			
			for (String subName : subNames) {
				SubscriberDescriptor descriptor = subscriberDescriptors.get(subName);
				try {
					Object newInstance = descriptor.getSubscriber();
					EPStatement statement = epAdmin.createEPL(epl);
					statement.setSubscriber(newInstance);
				} catch (InstantiationException e) {
					logger.warn(e.getMessage());
				} catch (IllegalAccessException e) {
					logger.warn(e.getMessage());
				} catch (NullPointerException e) {
					logger.warn(e.getMessage());
				}
			}
		}
	}

	public void sendEvent(Object event) {
		try {
			epService.getEPRuntime().sendEvent(event);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	class KRPropertiesConfiguration extends PropertiesConfiguration {

		public KRPropertiesConfiguration() {
			super();
		}

		public KRPropertiesConfiguration(File file) throws ConfigurationException {
			super(file);
		}

		public KRPropertiesConfiguration(URL url) throws ConfigurationException {
			super(url);
		}

		public KRPropertiesConfiguration(String fileName) throws ConfigurationException {
			super(fileName);
		}

		@Override
		public String getString(String key, String defaultValue) {
			String value = super.getString(key, defaultValue);
			return convertToUtf8(value);
		}

		@Override
		public String[] getStringArray(String arg0) {
			String[] array = super.getStringArray(arg0);
			for (int i = 0; i < array.length; i++) {
				array[i] = convertToUtf8(array[i]);
			}
			return array;
		}
		
		private String convertToUtf8(String string) {
			try {
				String s = new String(string.getBytes("ISO-8859-1"), "UTF-8");
				return s;
			} catch (UnsupportedEncodingException e) {
			}
			return string;
		}
	}
}
