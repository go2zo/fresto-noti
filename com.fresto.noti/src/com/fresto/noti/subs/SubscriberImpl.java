package com.fresto.noti.subs;

import java.util.Map;

public class SubscriberImpl implements Subscriber {

	protected String id;
	protected Map<?, ?> params;
	
	@Override
	public void setIdentifier(String id) {
		this.id = id;
	}

	@Override
	public void putParams(Map<?, ?> params) {
		this.params = params;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName()).append("{");
		sb.append("id=").append(id);
		sb.append(", params=").append(params.toString());
		sb.append("}");
		return sb.toString();
	}

}
