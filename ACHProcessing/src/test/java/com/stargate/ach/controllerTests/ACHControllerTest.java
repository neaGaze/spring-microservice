package com.stargate.ach.controllerTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import com.stargate.ach.service.ACHService;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.*;
@RunWith(SpringRunner.class)
@WebMvcTest
public class ACHControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Mock
	ACHService transactionService;

	@Mock
	RestTemplate restTemplate;

	@Test
	public void postTransactionTest() throws Exception {
		/*BLTransaction mockTxn = null;

		// transactionService.addTransaction to respond back with mockTransaction
		Mockito.when(
				transactionService.addTransaction(Mockito.any(BLTransaction.class))).thenReturn(mockTxn);
		 */
		// Send transaction as body to /URL path
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Matchers.anyString())
				.accept(Matchers.<MediaType>any())
				.contentType(Matchers.<MediaType>any());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/ach/transaction/1",
				response.getHeader(HttpHeaders.LOCATION));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void executeTransferTest() throws Exception{

		ResponseStatus actualResult = new ResponseStatus("Success", "");

		@SuppressWarnings("rawtypes")
		ResponseEntity responseEntity = new ResponseEntity(new ResponseStatus("Success", "Fail"), HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(Matchers.anyString()
				, Matchers.eq(HttpMethod.POST)
				, Matchers.<HttpEntity<Transaction>>any(),
				Matchers.any(Class.class)))
		.thenReturn(responseEntity);

		ResponseStatus foundResult = (ResponseStatus)responseEntity.getBody();
		assertEquals(foundResult.getStatus(), actualResult.getStatus());

	}

	@Test
	public void updateStatusTest() throws Exception{
		Integer actualStatus = null;
		Mockito.when(
				transactionService.updateStatus(null, Mockito.anyString()))
		.thenReturn(actualStatus);

		// Send status as body to /URL path
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(Matchers.anyString())
				.accept(Matchers.<MediaType>any())
				.contentType(Matchers.<MediaType>any());

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		/*assertEquals("http://localhost/ach/transaction/1",
				response.getHeader(HttpHeaders.LOCATION));*/
	}
}

