package com.stargate.transferfund.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class JMSMessageDelayCalculatorUtilTests {

	@Test
	public void whenGivenCurrentTime_thenFindDelayDifferenceSuccess() {
		Integer delayTime = 3600000;
		Long expectedTimeInMilli = (long) 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentTime = null;
		try {
			currentTime = sdf.parse("02-2018-01 05:31:00");
			expectedTimeInMilli = sdf.parse("02-2018-01 06:00:00").getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Long expectedDifference =  expectedTimeInMilli - currentTime.getTime();
		
		Long actualTimeInMilli = JMSMessageDelayCalculatorUtil.getDelayTime(delayTime, currentTime);
		System.out.println("expectedtime Diff: " + expectedDifference);
		assertThat(actualTimeInMilli).isEqualTo(expectedDifference);		
	}
}
