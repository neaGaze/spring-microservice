package com.stargate.transferfund.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.repository.BankRepository;

@Service
public class TransferServiceImpl implements TransferService{
	
	@Autowired
	private BankRepository bankRepository;
	/*
	@Autowired
	private RestTemplate restTemplate;
	*/
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${jms.queue.destination}")
	String destinationQueue;

	@Override
	public List<Bank> findAll() {
		List<Bank> listBank = bankRepository.findAll();
		for(int i = 0; i < listBank.size(); i++) {
			System.out.println(listBank.get(i).getBankName());
		}
		return listBank;
	}

	@Override
	public boolean dumpFlatFile(Transaction transaction)  {
/*
		HttpEntity<Transaction> request = new HttpEntity<Transaction>(transaction);
		ResponseEntity<ResponseStatus> response = null;
		try {
			response = restTemplate.exchange("http://www.google.com", HttpMethod.POST, request, ResponseStatus.class);
		
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		if(response.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED) throw new InvalidRequestException(); 
		
		ResponseStatus status = response.getBody();
		if(status.getStatus() == "Success") return true; else return false;
		*/
		System.out.println("Sending a transaction.");

		jmsTemplate.setDeliveryDelay(5000);
		jmsTemplate.convertAndSend(destinationQueue, transaction);
		
		return true;
	}

}
