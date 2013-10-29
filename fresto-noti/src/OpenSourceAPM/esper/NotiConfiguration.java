package OpenSourceAPM.esper;

import org.apache.commons.configuration.PropertiesConfiguration;

public class NotiConfiguration extends PropertiesConfiguration {

	private static NotiConfiguration INSTANCE;
	
	public static NotiConfiguration getInstance() {
		if (INSTANCE != null) {
			INSTANCE = new NotiConfiguration();
		}
		return INSTANCE;
	}
}
