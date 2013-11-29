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

import java.util.Map;

public interface Subscriber {
	static final String TYPE_MAIL = "mail"; //$NON-NLS-1$
	static final String TYPE_JS = "js"; //$NON-NLS-1$
	static final String TYPE_CONSOLE = "console"; //$NON-NLS-1$
	
	static final String SUBJECT = "subject"; //$NON-NLS-1$
	static final String CONTENT = "message"; //$NON-NLS-1$
	
	void setIdentifier(String id);
	void putParams(Map<?, ?> params);

}
