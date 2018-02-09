package com.stargate.transferfund.controller;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.stargate.transferfund.entity.Account;
import com.stargate.transferfund.entity.AccountType;
import com.stargate.transferfund.entity.ResponseStatus;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransactionType;
import com.stargate.transferfund.service.TransferService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(TransferController.class)
public class TransferControllerTest {

	@Autowired
    private MockMvc mvc;
	
	private Account senderDetails, receiverDetails;
	private Transaction transaction;
	
    @MockBean
    private TransferService transferService;
    

    @Before
    public void setup() {
    	senderDetails = new Account();
    	senderDetails.setAccountNumber("54766100");
    	senderDetails.setAccountType(AccountType.CHECKING);
    	senderDetails.setBankId(304);
    	senderDetails.setBankName("Banke Suisse");
    	senderDetails.setName("Jamal Harrarri");
    	senderDetails.setRoutingNumber("1108109665");
    	

    	receiverDetails = new Account();
    	receiverDetails.setAccountNumber("067780012");
    	receiverDetails.setAccountType(AccountType.CHECKING);
    	receiverDetails.setBankId(404);
    	receiverDetails.setBankName("Bank of Canada");
    	receiverDetails.setName("Jessica Cortes");
    	receiverDetails.setRoutingNumber("6672109665");
    	
    	transaction = new Transaction();
    	transaction.setAmount(450.00);
    	transaction.setTransactionType(TransactionType.DEBIT);
    	transaction.setSenderDetails(senderDetails);
    	transaction.setReceiverDetails(receiverDetails);
    }
    
    @Test
    public void givenTransaction_whenGetTransferDetails_thenReturnResponseStatus() {
    	// given
    	ResponseStatus responseStatus = new ResponseStatus();
    	//when(transferService.transfertoJMS(transaction)).thenReturn(responseStatus);
/*
        given(service.getAllEmployees()).willReturn(allEmployees);
     
        mvc.perform(get("/api/employees")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name", is(alex.getName())));
    	
*/    	
    	// when
    	
    	// then
    }
}
