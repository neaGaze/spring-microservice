package com.stargate.transferfund.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.repository.BankRepository;

@Service
public class TransferServiceImpl implements TransferService{
	
	@Autowired
	private BankRepository bankRepository;
	
	@Override
	public List<Bank> findAll() {
		List<Bank> listBank = bankRepository.findAll();
		for(int i = 0; i < listBank.size(); i++) {
			System.out.println(listBank.get(i).getBankName());
		}
		return listBank;
	}

	@Override
	public void dumpFlatFile(Transaction transaction) {
		RestTemplate restTemplate = new RestTemplateBuilder().build();
		HttpEntity<Transaction> request = new HttpEntity<Transaction>(transaction);
		ResponseEntity response = restTemplate.exchange("http://www.google.com", HttpMethod.POST, request, Boolean.class);
		/*
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		Foo foo = response.getBody();
		assertThat(foo, notNullValue());
		assertThat(foo.getName(), is("bar"));
		*/
	}

}
