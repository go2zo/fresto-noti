package com.fresto.noti.extensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public class SubscriberRegistry {

	public static final String SUBSCRIBERS_EXTENSION_ID = "subscribers";
	
	private static SubscriberRegistry instance;
	
	public static SubscriberRegistry getInstance() {
		if (instance == null) {
			instance = new SubscriberRegistry();
		}
		return instance;
	}
	
	private Map<String, SubscriberDescriptor> subscriberDescriptors;
	
	private String namespace;
	
	protected SubscriberRegistry() {
		initSubscriberDescriptors();
	}
	
	private void initSubscriberDescriptors() {
		subscriberDescriptors = new HashMap<String, SubscriberDescriptor>();
		
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(namespace, SUBSCRIBERS_EXTENSION_ID);
		
		SubscriberFactory factory = new SubscriberFactory();
		
		for (IConfigurationElement ele : configurationElements) {
			SubscriberDescriptor desc;
			try {
				if (SubscriberFactory.SUBSCRIBER_EXTENSIONPOINT.equals(ele.getName())) {
					desc = factory.createSubscriberDescriptor(ele);

					if (subscriberDescriptors.get(desc.getContextId()) != null) {
						SubscriberDescriptor existingDesc = subscriberDescriptors
								.get(desc.getContextId());
						if (desc.equals(existingDesc)) {
							// TODO log
						}
					} else {
						subscriberDescriptors.put(desc.getContextId(), desc);
					}
				}
			} catch (ExtensionException e) {
				// TODO log
			}
		}
	}
	
	public Object getSubscriber(Object key) {
		SubscriberDescriptor desc = subscriberDescriptors.get(key);
		return desc.getSubscriber();
	}
	
	public List<Object> getSubscribers() {
		List<Object> result = new ArrayList<>();
		for (SubscriberDescriptor desc : subscriberDescriptors.values()) {
			result.add(desc.getSubscriber());
		}
		return result;
	}
	
	public SubscriberDescriptor getSubscriberDescriptor(Object id) {
		return subscriberDescriptors.get(id);
	}
	
	public List<SubscriberDescriptor> getSubscriberDescriptors() {
		return new ArrayList<>(subscriberDescriptors.values());
	}
	
}
