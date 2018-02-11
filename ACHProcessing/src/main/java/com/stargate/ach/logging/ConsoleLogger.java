package com.stargate.ach.logging;


import java.util.Iterator;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger extends BaseLogger {
	
	public ConsoleLogger() {
		this.log = LoggerFactory.getLogger(this.getClass());
	}
	
	public ConsoleLogger(Class controlLevelPath) {
		super(controlLevelPath);
		this.log = LoggerFactory.getLogger(controlLevelPath);
	}
	
	public ConsoleLogger(Class controlLevelPath, String logMsg) {
		super(controlLevelPath, logMsg);
		this.log = LoggerFactory.getLogger(controlLevelPath);
	}

	@Override
	public void writeLogs() {
		log.info(getMessage());
		
		// check to see if it has previous successors that needs to be logged
		if(listOfOldSuccessors != null) {
			Iterator<BaseLogger> iter = listOfOldSuccessors.iterator();
			while(iter.hasNext()) {
				BaseLogger oldLogger = iter.next();
				oldLogger.writeLogs();
			}
		}
		
		// iterate through all of its successors and print all their logs in succession in the console
		if(successor != null) 
			successor.writeLogs();
	}
}
