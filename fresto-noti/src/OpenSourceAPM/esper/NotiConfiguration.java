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
