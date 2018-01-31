package com.stargate.controllerTest;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.stargate.controller.GetBalanceController;
import com.stargate.repository.GetBalanceRepository;

public class GetBalanceControllerUnitTest {
	
	
	@RunWith(SpringRunner.class)
	@WebMvcTest
	public class getBalanceUnitTest {
	       
	       @Autowired
	       MockMvc mockMvc;
	       @Mock
	    private GetBalanceRepository repo;

	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	    }
	    
	    @Test
	    public void testGetBalance() throws Exception{
	       Mockito.when(repo.findAll()).thenReturn(Collections.emptyList());
	       
	       MvcResult mvcResult = mockMvc
	              .perform(MockMvcRequestBuilders.get("/demo/find"))
	              .andReturn();
	       System.out.println(mvcResult.getResponse());
	       Mockito.verify(repo).findBalance("123355");
	    }
	    
	    
	    
	}
}
	




