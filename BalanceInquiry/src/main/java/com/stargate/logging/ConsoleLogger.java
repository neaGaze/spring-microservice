package com.stargate.logging;

public class ConsoleLogger extends BaseLogger {

	public ConsoleLogger(String controlLevelPath, String logMsg) {
		this.logMessage = logMsg;
		this.controlLevelPath = controlLevelPath;
	}
}
