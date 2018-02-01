package com.stargate.transferfund.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;

public class GlobalUtils {

	/**********************************************************************************
	 * Logic for calculating the delivery time of the JMS message 
	 ************************************************************************************/
	public static long getDelayTime(Integer delayTime) {
		FastDateFormat formatter = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();
		long currentTimeInMillis = cal.getTimeInMillis();
		
		 // Get nearest hour
        Date nearestHour = DateUtils.round(currentTime, Calendar.HOUR);
        DateUtils.setMinutes(currentTime, 0);
        DateUtils.setSeconds(currentTime, 0);

        System.out.println("nearestRoundedHour = " + formatter.format(nearestHour));
        return nearestHour.getTime() - currentTime.getTime();
	}
}
