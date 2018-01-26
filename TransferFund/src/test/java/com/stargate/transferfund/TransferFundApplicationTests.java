package com.stargate.transferfund;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Import(MyTestsConfiguration.class)
public class TransferFundApplicationTests {


    @Autowired
    private TestEntityManager entityManager;
    
	@Test
	public void contextLoads() {
		
	}

}
