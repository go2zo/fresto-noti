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

public class PageCallEvent {
	private String ipAddress;
	private String uuid;
	private long responseTime;
	private String url;
	
	public PageCallEvent(String IP_Address, String UUID, long ResponseTime,String URL){
		super();
		this.ipAddress = IP_Address;
		this.uuid = UUID;	
		this.responseTime = ResponseTime;
		this.url = URL;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getUuid(){
		return uuid;
	}
	public long getResponseTime(){
		return responseTime;
	}
	public String getUrl(){
		return url;
	}
	
	
	
	

}
