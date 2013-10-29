package com.fresto.noti.extensions;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class SubscriberFactory {

	public static final String SUBSCRIBER_EXTENSIONPOINT = "subscriber";
	
	private static final String ID_ATTRIBUTE = "id";
	
	private static final String EPL_ATTRIBUTE = "epl";
	
	private static final String CLASS_ATTRIBUTE = "class";

	public SubscriberDescriptor createSubscriberDescriptor(IConfigurationElement element) throws ExtensionException {
		SubscriberDescriptor desc = new SubscriberDescriptor();
		desc.setContextId( element.getAttribute(ID_ATTRIBUTE) );
		desc.setEpl( element.getAttribute(EPL_ATTRIBUTE) );
		desc.setContextClass(parseClass(element, CLASS_ATTRIBUTE));
		return desc;
	}
	
	protected Class<?> parseClass(IConfigurationElement element, String attrName) throws ExtensionException {
		String className = element.getAttribute(attrName);
		
		if (className == null || className.length() == 0) {
			// TODO extend exception
			throw new ExtensionException();
		}
		
		Class<?> factoryClass;
		try {
			factoryClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			try {
				String declaringID = element.getContributor().getName();
				Bundle bundle = Platform.getBundle(declaringID);
				factoryClass = bundle.loadClass(className);
			} catch (ClassNotFoundException e1) {
				// TODO extend exception
				throw new ExtensionException(e1);
			}
		}
		return factoryClass;
	}
}
