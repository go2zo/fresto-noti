package com.fresto.noti.subs;

import java.util.Map;

public interface Subscriber {
	
	void setIdentifier(String id);
	void putParams(Map<?, ?> params);

}
