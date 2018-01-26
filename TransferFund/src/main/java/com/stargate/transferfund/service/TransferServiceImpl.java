package com.stargate.transferfund.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.ResponseStatus;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.exception.InvalidRequestException;
import com.stargate.transferfund.repository.BankRepository;

@Service
public class TransferServiceImpl implements TransferService{
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Bank> findAll() {
		List<Bank> listBank = bankRepository.findAll();
		for(int i = 0; i < listBank.size(); i++) {
			System.out.println(listBank.get(i).getBankName());
		}
		return listBank;
	}

	@Override
	public boolean dumpFlatFile(Transaction transaction) throws InvalidRequestException {

		HttpEntity<Transaction> request = new HttpEntity<Transaction>(transaction);
		ResponseEntity<ResponseStatus> response = null;
		try {
			response = restTemplate.exchange("http://www.google.com", HttpMethod.POST, request, ResponseStatus.class);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		/*
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		Foo foo = response.getBody();
		assertThat(foo, notNullValue());
		assertThat(foo.getName(), is("bar"));
		*/
		if(response.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) throw new InvalidRequestException(); 
		
		ResponseStatus status = response.getBody();
		if(status.getStatus() == "Success") return true; else return false;
	}

}
