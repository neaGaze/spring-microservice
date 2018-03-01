package com.stargate.transferfund.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.stargate.transferfund.logging.BaseLogger;

@Component
public class JMSMessageDelayCalculatorUtil {
	
	@Autowired
	private BaseLogger jmsDelayCalcLogger;
	
	public JMSMessageDelayCalculatorUtil() {
	}
	
	/**********************************************************************************
	 * Logic for calculating the delivery time of the JMS message.
	 * Unit Test cases {@link com.stargate.transferfund.util.JMSMessageDelayCalculatorUtilTests.java}
	 ************************************************************************************/
	public long getDelayTime(Integer delayTime, Date currentTime) {
		long curTime = currentTime.getTime();
        long newTime = (((curTime / delayTime) * delayTime) + delayTime) - curTime;
        jmsDelayCalcLogger.appendMessages("CurTime in milli: " + curTime + " and delayTime: " + (newTime/1000) + " secs");
        return newTime;
	}
}
