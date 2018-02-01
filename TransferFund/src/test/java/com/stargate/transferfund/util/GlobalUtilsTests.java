package com.stargate.transferfund.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GlobalUtilsTests {

	@Test
	public void when_thenSuccess() {
		Integer delayTime = 3600000;
		
		//long expectedTimeInMilli = 
		long actualTimeInMilli = GlobalUtils.getDelayTime(delayTime);
		
	}
}
