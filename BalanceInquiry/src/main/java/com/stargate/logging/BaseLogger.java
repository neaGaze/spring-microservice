package com.stargate.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseLogger {

	// Define the logger object for this class
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BaseLogger successor;
	protected String logMessage, controlLevelPath;

	protected String getMessage() {
		return logMessage;
	}

	protected String getControlLevelPath() {
		return controlLevelPath;
	}
	
	public BaseLogger getSuccessor() {
		return successor;
	}

	public void setSuccessor(BaseLogger successor) {
		this.successor = successor;
	}

	public void writeLogs() {
		log.debug(getMessage());
		// now iterate through all of its successors and print all their logs in succession
		successor.writeLogs();
	}
}
