package com.stargate.ach.logging;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseLogger {

	// Define the logger object for this class
	protected Logger log;
	
	protected BaseLogger successor;
	protected StringBuilder logMsgBuilder;
	protected String controlLevelPath;
	
	protected List<BaseLogger> listOfOldSuccessors;
	
	public BaseLogger() {}
	
	public BaseLogger(Class controlLevelPath) {
		this.controlLevelPath = controlLevelPath.getName();
	}
	
	public BaseLogger(Class controlLevelPath, String logMsg) {
		this.appendMessages(logMsg);
		this.controlLevelPath = controlLevelPath.getName();
	}
	
	protected String getMessage() {
		return logMsgBuilder.toString();
	}
	
	public void appendMessages(String msg) {
		if(logMsgBuilder == null)
			logMsgBuilder = new StringBuilder(msg);
		
		logMsgBuilder.append("\n" + msg);
	}

	protected String getControlLevelPath() {
		return controlLevelPath;
	}
	
	public void setControlLevelPath(String controlLevelPath) {
		this.controlLevelPath = controlLevelPath;
	}
	
	public BaseLogger getSuccessor() {
		return successor;
	}

	public void setSuccessor(BaseLogger successor) {
		if(this.successor != null) {
			if(listOfOldSuccessors == null) listOfOldSuccessors = new ArrayList<BaseLogger>();
			listOfOldSuccessors.add(this.successor);
		}

		this.successor = successor;
	}

	abstract public void writeLogs();
}

