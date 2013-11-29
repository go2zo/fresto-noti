package com.fresto.core;

public interface Topics {
	
	// Client Events
	static final String TOPIC_REQUEST = "CB";
	static final String TOPIC_RESPONSE = "CF";

	// Server Events
	static final String TOPIC_ENTRY_CALL = "EB";
	static final String TOPIC_ENTRY_RETURN = "EF";
	static final String TOPIC_OPERATION_CALL = "OB";
	static final String TOPIC_OPERATION_RETURN = "OF";
	static final String TOPIC_SQL_CALL = "SB";
	static final String TOPIC_SQL_RETURN = "SF";

	// Command Events
	static final String TOPIC_COMMAND_EVENT = "CMD";

}
