package com.stargate.logging;

public class FileLogger extends BaseLogger {

	public FileLogger(String logMsg, String controlLevelPath) {
		//this.logMessage = logMsg;
		this.controlLevelPath = controlLevelPath;
	}

	@Override
	public void writeLogs() {
		
	}
}
