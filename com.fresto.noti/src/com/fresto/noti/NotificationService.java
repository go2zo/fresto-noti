package com.fresto.noti;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class NotificationService {
	
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
	private static final String CLASS = "{0}.class"; //$NON-NLS-1$
	
	private EPServiceProvider epService;
	
	private Map<String, List<String>> subscriberMap;
	private Map<String, Class<?>> subscriberClasses;
	
	private NotificationService() {
		init();
		start();
	}
	
	private void init() {
		subscriberMap = new HashMap<>();
		subscriberClasses = new HashMap<>();

		PropertiesConfiguration configuration = null;
		try {
			configuration = new PropertiesConfiguration("notification.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		String[] notis = configuration.getStringArray(NOTIFICATIONS);
		for (int i = 0; i < notis.length; i++) {
			String eplKey = MessageFormat.format(EPL, notis[i]);
			String subKey = MessageFormat.format(SUBSCRIBERS, notis[i]);

			String epl = configuration.getString(eplKey);
			String[] subs = configuration.getStringArray(subKey);
			List<String> notiList = new ArrayList<>();
			for (int j = 0; j < subs.length; j++) {
				String classKey = MessageFormat.format(CLASS, subs[j]);
				String className = configuration.getString(classKey);
				notiList.add(subs[j]);
				try {
					Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass(className);
					subscriberClasses.put(subs[j], clazz);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

			subscriberMap.put(epl, notiList);
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
			List<String> subscribers = subscriberMap.get(epl);
			
			for (String subscriber : subscribers) {
				Class<?> subscriberClass = subscriberClasses.get(subscriber);
				try {
					Object newInstance = subscriberClass.newInstance();
					EPStatement statement = epAdmin.createEPL(epl);
					statement.setSubscriber(newInstance);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
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
