package com.stargate.transferfund.service;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransactionType;
import com.stargate.transferfund.entity.TransferRequest;
import com.stargate.transferfund.exception.FailedDBUpdateException;
import com.stargate.transferfund.repository.BankRepository;
import com.stargate.transferfund.util.JMSMessageDelayCalculatorUtil;

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
	
	@Value("${stargate.jms.delaytime}")
	Integer delayTime;

	@Override
	public List<Bank> findAll() {
		List<Bank> listBank = bankRepository.findAll();
		for(int i = 0; i < listBank.size(); i++) {
			System.out.println(listBank.get(i).getBankName());
		}
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
		return listBank;
	}

	/******************************************************
	 * Transfer the transacation message to JMS for scheduling the delivery 
	 ******************************************************/
	@Override
	public boolean transfertoJMS(Transaction transaction)  {

		System.out.println("Sending a transaction.");

		long timeToDeliver = JMSMessageDelayCalculatorUtil.getDelayTime(delayTime, Calendar.getInstance().getTime());
		System.out.println("expected Delivery TIme: " + (timeToDeliver / 1000) + " secs");
		jmsTemplate.setDeliveryDelay(timeToDeliver);
		jmsTemplate.convertAndSend(destinationQueue, transaction);
		
		return true;
	}

	/******************************************************
	 * To update the one directional transfer  
	 *******************************************************/
	@Override
	public void updateUniTransfer(TransferRequest transferRequest) throws FailedDBUpdateException{
		int amt = -1;
		if(transferRequest.getTransactionType() == TransactionType.DEBIT) {
			  amt = bankRepository.debitBankBalance(transferRequest.getAccount().getBankId(), transferRequest.getAmount()); 
				
		} else if(transferRequest.getTransactionType() == TransactionType.CREDIT) {
			 amt = bankRepository.creditBankBalance(transferRequest.getAccount().getBankId(), transferRequest.getAmount());			
		}
		
		if(amt <= 0) throw new FailedDBUpdateException();
	}
}
