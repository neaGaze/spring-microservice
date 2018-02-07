package com.stargate.transferfund.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.activemq.artemis.junit.EmbeddedJMSResource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.entity.ResponseStatus;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.exception.InvalidRequestException;
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
	
	@Rule
	public EmbeddedJMSResource resource = new EmbeddedJMSResource();
	
	@Before
	public void setUp() {
		ResponseStatus status = new ResponseStatus();
		status.setStatus("Success");
	
		ResponseEntity<ResponseStatus> responseEntity = new ResponseEntity<ResponseStatus>(status, HttpStatus.ACCEPTED);
		
		Mockito.when(restTemplate.exchange(
				Matchers.anyString(), 
				Matchers.eq(HttpMethod.POST), 
				Matchers.<HttpEntity<Transaction>>any(), 
				Matchers.any(Class.class)))
		.thenReturn(responseEntity);
	}
	
	@Test
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
