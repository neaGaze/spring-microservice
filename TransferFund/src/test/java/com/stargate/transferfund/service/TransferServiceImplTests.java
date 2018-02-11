package com.stargate.transferfund.service;

import org.apache.activemq.artemis.junit.EmbeddedJMSResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.business.entity.BLTransferRequest;
import com.stargate.transferfund.entity.Account;
import com.stargate.transferfund.entity.AccountType;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransactionType;
import com.stargate.transferfund.entity.TransferRequest;
import com.stargate.transferfund.exception.FailedDBUpdateException;
import com.stargate.transferfund.logging.BaseLogger;
import com.stargate.transferfund.repository.BankRepository;

@RunWith(SpringRunner.class)
public class TransferServiceImplTests {
	
	@Mock
	private BankRepository bankRepository;

	@Mock
	private TransferService transferService;
	
	@InjectMocks
	TransferServiceImpl transferServiceImpl;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private BaseLogger logger;
	
	@Rule
	public EmbeddedJMSResource resource = new EmbeddedJMSResource();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
	/*	ResponseStatus status = new ResponseStatus();
		status.setStatus("Success");
	
		ResponseEntity<ResponseStatus> responseEntity = new ResponseEntity<ResponseStatus>(status, HttpStatus.ACCEPTED);
		
		Mockito.when(restTemplate.exchange(
				Matchers.anyString(), 
				Matchers.eq(HttpMethod.POST), 
				Matchers.<HttpEntity<Transaction>>any(), 
				Matchers.any(Class.class)))
		.thenReturn(responseEntity);*/
	}
	
	@Test
	public void whenValidTransferDebitRequest_thenUpdateSuccess() {
		int amt = -1;
		
		Account account1 = new Account();
		account1.setAccountNumber("45688890");
		account1.setAccountType(AccountType.CHECKING);
		account1.setBankId(101);
		account1.setBankName("AIG");
		account1.setName("Alexander Kreshchinov");
		account1.setRoutingNumber("222108893");
		
		BLTransferRequest transferRequest = new BLTransferRequest();
		transferRequest.setAccountNo(account1.getAccountNumber());
		transferRequest.setAmount(300.0);
		transferRequest.setTransactionType(TransactionType.DEBIT);
		
		Mockito.when(bankRepository.debitBankBalance(transferRequest.getAccountNo(),
				transferRequest.getAmount())).thenReturn(1);
		
		try {
			transferService.updateUniTransfer(transferRequest);
			assert(true);
		} catch (FailedDBUpdateException e) {
			e.printStackTrace();
			Assert.fail("The test has failed due to an unexpected exception: " + e.getMessage()); // or just re-throw this exception
	    } 
	}
	

	@Test 
	public void whenInvalidTransferCreditRequest_thenUpdateFail() throws Exception {
		
		Account account2 = new Account();
		account2.setAccountNumber("33766100");
		account2.setAccountType(AccountType.SAVINGS);
		account2.setBankId(204);
		account2.setBankName("Royal Bank of Scotland");
		account2.setName("Bill Maher");
		account2.setRoutingNumber("0998109665");

		BLTransferRequest transferRequest = new BLTransferRequest();
		transferRequest.setAccountNo(account2.getAccountNumber());
		transferRequest.setAmount(1400.0);
		transferRequest.setTransactionType(TransactionType.CREDIT);
	
		Mockito.when(bankRepository.creditBankBalance(transferRequest.getAccountNo(),
				transferRequest.getAmount())).thenReturn(-1);
		
		// arrange
	    thrown.expect(FailedDBUpdateException.class);
	    thrown.expectMessage("Failed to execute transfers!!");
	    
	    // act
		transferService.updateUniTransfer(transferRequest);		
	}
	
	//@Test
	public void whenValidRequest_thenSuccess() {
		//boolean expectedResult = true;
		
		Transaction transaction = new Transaction();
		transaction.setAmount(59.99);
		//boolean actualResult = false;
		try {
			 transferServiceImpl.transfertoJMS(transaction);
		} finally {
			//assertThat(actualResult).isEqualTo(expectedResult);
		}
	}
	
}
