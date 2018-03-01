package com.stargate.status.unitTest;

import org.junit.Before;
import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.Test;


import static org.junit.Assert.*;

import com.stargate.status.controller.GetStatusController;
import com.stargate.status.entity.Transaction;
import com.stargate.status.repository.GetStatusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(GetStatusController.class)
public class GetStatusControllerUnitTest {

		
		@Autowired
		MockMvc mockMvc;
		@MockBean
	    private GetStatusRepository repo;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	    }
	    
	    @Test
	    public void testGetStatus() throws Exception{
	    	Transaction txn = new Transaction();
	    	txn.setId("10");
	    	txn.setStatus("InProgress");
	    	Mockito.when(repo.findOne("10")).thenReturn(txn);
	    	
	    	MvcResult mvcResult = mockMvc
	    		.perform(MockMvcRequestBuilders.get("/transaction/find"))
	    		.andReturn();
	    assertEquals(txn.getStatus(), "InProgress");
	    }
	    
	    
	    
	}

