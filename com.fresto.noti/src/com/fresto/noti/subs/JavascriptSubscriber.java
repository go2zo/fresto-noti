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

import java.text.MessageFormat;
import java.util.Map;

public class JavascriptSubscriber implements Subscriber {
	
	private Map<?, ?> params;
	
	@Override
	public void setIdentifier(String id) {
	}

	@Override
	public void putParams(Map<?, ?> params) {
		this.params = params;
	}
	
	public void update(Object[] row) {
		String message = (String)params.get(CONTENT);
		message = MessageFormat.format(message, row);
		
	}
	
}
