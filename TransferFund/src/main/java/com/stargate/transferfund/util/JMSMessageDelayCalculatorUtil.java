package com.stargate.transferfund.util;

import java.util.Date;


public class JMSMessageDelayCalculatorUtil {

	/**********************************************************************************
	 * Logic for calculating the delivery time of the JMS message.
	 * Unit Test cases {@link com.stargate.transferfund.util.JMSMessageDelayCalculatorUtilTests.java}
	 ************************************************************************************/
	public static long getDelayTime(Integer delayTime, Date currentTime) {
        
        long curTime = currentTime.getTime();
        long newTime = (((curTime / delayTime) * delayTime) + delayTime) - curTime;
        System.out.println("CurTime in milli: " + curTime + " and delayTime: " + delayTime);
        return newTime;
	}
}
