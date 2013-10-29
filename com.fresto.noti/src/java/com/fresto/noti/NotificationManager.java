package com.fresto.noti;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.fresto.noti.extensions.SubscriberDescriptor;
import com.fresto.noti.extensions.SubscriberRegistry;

public class NotificationManager {
	
	private static NotificationManager instance;
	
	public static NotificationManager getInstance() {
		if (instance == null) {
			instance = new NotificationManager();
		}
		return instance;
	}
	
	private static EPServiceProvider epService;
	
	public void start() {
		Configuration config = new Configuration();
		
		epService = EPServiceProviderManager.getDefaultProvider(config);
		EPAdministrator epAdmin = epService.getEPAdministrator();
		
		SubscriberRegistry registry = SubscriberRegistry.getInstance();
		for (SubscriberDescriptor descriptor : registry.getSubscriberDescriptors()) {
			EPStatement statement = epAdmin.createEPL(descriptor.getEpl());
			statement.setSubscriber(descriptor.getSubscriber());
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
