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

import com.fresto.noti.NotificationHub;
import com.fresto.noti.NotificationStreamer;
import com.fresto.noti.NotificationWriter;

public class noti {
	
	public static void main(String[] args) {
		new NotificationHub().start();
		new NotificationStreamer().start();
		new NotificationWriter().start();
	}

}
