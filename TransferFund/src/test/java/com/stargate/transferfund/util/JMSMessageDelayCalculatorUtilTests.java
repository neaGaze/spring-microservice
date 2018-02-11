package com.stargate.transferfund.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.stargate.transferfund.logging.BaseLogger;

@RunWith(SpringRunner.class)
public class JMSMessageDelayCalculatorUtilTests {

	@Mock
	BaseLogger baseLogger;
	
	@Mock
	JMSMessageDelayCalculatorUtil jmsMessageDelayCalculatorUtil;
	
	@Test
	public void whenGivenCurrentTime_thenFindDelayDifferenceSuccess() {
		Integer delayTime = 20000;
		Long expectedTimeInMilli = (long) 0;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy-dd HH:mm:ss");
		Date currentTime = null;
		try {
			// Uncomment these if you want to set time in milliseconds
			//Calendar cal = Calendar.getInstance();
			//cal.setTimeInMillis(1518053848685);
			//currentTime = cal.getTime();
			currentTime = sdf.parse("02-2018-01 18:38:13");
			expectedTimeInMilli = sdf.parse("02-2018-01 18:38:20").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		Long expectedDifference =  expectedTimeInMilli - currentTime.getTime();
		Long actualTimeDiffInMilli = jmsMessageDelayCalculatorUtil.getDelayTime(delayTime, currentTime);
		System.out.println("expectedtime Diff: " + (actualTimeDiffInMilli / 1000) + " secs");
		assertThat(actualTimeDiffInMilli).isEqualTo(expectedDifference);		
	}
}
