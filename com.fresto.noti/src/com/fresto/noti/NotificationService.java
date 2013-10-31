package com.fresto.noti;

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
import com.fresto.noti.subs.SubscriberDescriptor;

public class NotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	private static NotificationService instance;
	
	public static NotificationService getInstance() {
		if (instance == null) {
			instance = new NotificationService();
		}
		return instance;
	}
	
	private static final String NOTIFICATIONS = "notifications"; //$NON-NLS-1$
	private static final String EPL = "{0}.epl"; //$NON-NLS-1$
	private static final String SUBSCRIBERS = "{0}.subscribers"; //$NON-NLS-1$
	private static final String MESSAGE = "{0}.message"; //$NON-NLS-1$
	private static final String MESSAGE_SUB = "{0}.message.{1}"; //$NON-NLS-1$
	private static final String CLASS = "{0}.class"; //$NON-NLS-1$
	
	private EPServiceProvider epService;
	
	private Map<String, List<String>> subscriberMap;
	private Map<String, SubscriberDescriptor> subscriberDescriptors;
	
	private NotificationService() {
		init();
		start();
	}
	
	private void init() {
		subscriberMap = new HashMap<>();
		subscriberDescriptors = new HashMap<>();

		PropertiesConfiguration configuration = null;
		try {
			configuration = new PropertiesConfiguration("notification.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		String[] notifications = configuration.getStringArray(NOTIFICATIONS);
		for (String notification : notifications) {
			String eplKey = MessageFormat.format(EPL, notification);
			String subKey = MessageFormat.format(SUBSCRIBERS, notification);
			String msgKey = MessageFormat.format(MESSAGE, notification);

			String epl = configuration.getString(eplKey);
			String[] subscribers = configuration.getStringArray(subKey);
			String message = configuration.getString(msgKey);
			
			List<String> subList = new ArrayList<>();
			for (String subscriber : subscribers) {
				String classKey = MessageFormat.format(CLASS, subscriber);
				String className = configuration.getString(classKey);
				
				subList.add(subscriber);
				
				// noti.message.sub
				if (message == null || message.equals("")) { //$NON-NLS-1$
					String msgSubKey = MessageFormat.format(MESSAGE_SUB, notification, subscriber);
					message = configuration.getString(msgSubKey);
				}
				
				try {
					Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
					SubscriberDescriptor descriptor = new SubscriberDescriptor(clazz);
					
					descriptor.addParam("subject", message); //$NON-NLS-1$
					descriptor.addParam("message", message); //$NON-NLS-1$
					
					Iterator<String> keys = configuration.getKeys(subscriber);
					while (keys.hasNext()) {
						String key = keys.next();
						String value = configuration.getString(key);
						
						logger.info("key=" + key + ", value=" + value);

						// remove subscriber name
						key = key.substring(key.indexOf('.') + 1);
						descriptor.addParam(key, value);
					}
					
					subscriberDescriptors.put(subscriber, descriptor);
				} catch (ClassNotFoundException e) {
					logger.warn(className + " cannot found.");
				}
			}

			subscriberMap.put(epl, subList);
		}
	}
	
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
	
}
