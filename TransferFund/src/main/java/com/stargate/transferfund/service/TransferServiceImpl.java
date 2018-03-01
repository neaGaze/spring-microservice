package com.stargate.transferfund.service;

import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.stargate.transferfund.business.entity.BLTransaction;
import com.stargate.transferfund.business.entity.BLTransferRequest;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransactionType;
import com.stargate.transferfund.exception.FailedDBUpdateException;
import com.stargate.transferfund.logging.BaseLogger;
import com.stargate.transferfund.repository.BankRepository;
import com.stargate.transferfund.util.JMSMessageDelayCalculatorUtil;
import com.stargate.transferfund.util.PrgmTxnToBsnTxnConverter;

@Service
public class TransferServiceImpl implements TransferService {
	
	@Autowired
	private BaseLogger transferToJMSLogger;

	@Autowired
	private BaseLogger updateUniDirTransferLogger;
	
	@Autowired
	private JMSMessageDelayCalculatorUtil jmsMessageDelayCalculatorUtil;
	
	@Autowired
	private BLTransaction blTransaction;
	
	@Autowired
	private PrgmTxnToBsnTxnConverter entityConverter;
	
	@Autowired
	private BankRepository bankRepository;
	/*
	 * @Autowired private RestTemplate restTemplate;
	 */

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${jms.queue.destination}")
	String destinationQueue;

	@Value("${stargate.jms.delaytime}")
	Integer delayTime;
	
	public TransferServiceImpl() {
		
	}

	/******************************************************
	 * Transfer the transaction message to JMS for scheduling the delivery
	 ******************************************************/
	@Override
	public void transfertoJMS(Transaction transaction) {
		transferToJMSLogger.appendMessages("Sending a transaction to JMS.");
		
		blTransaction = entityConverter.convert(transaction);
		//blTransaction.decorateBLTransaction(transaction);
		
		long timeToDeliver = jmsMessageDelayCalculatorUtil.getDelayTime(delayTime, Calendar.getInstance().getTime());
		transferToJMSLogger.appendMessages("Expected Delivery Time: " + (timeToDeliver / 1000) + " secs");
		jmsTemplate.setDeliveryDelay(timeToDeliver);
		
		jmsTemplate.convertAndSend(destinationQueue, blTransaction);
		
		transferToJMSLogger.appendMessages("Successful tranfer to JMS");
	}

	/******************************************************
	 * To update the one directional transfer
	 *******************************************************/
	@Override
	public void updateUniTransfer(BLTransferRequest transferRequest) throws FailedDBUpdateException {
		int amt = -1;
		if (transferRequest.getTransactionType() == TransactionType.DEBIT) {
			amt = bankRepository.debitBankBalance(transferRequest.getAccountNo(),
					transferRequest.getAmount());
		}
		
		if (transferRequest.getTransactionType() == TransactionType.CREDIT) {
			amt = bankRepository.creditBankBalance(transferRequest.getAccountNo(),
					transferRequest.getAmount());
		}

		if (amt <= 0)
			throw new FailedDBUpdateException();
		else 
			updateUniDirTransferLogger.appendMessages(transferRequest.getTransactionType() + "ing bank balance for " +
				" for the account no: " + transferRequest.getAccountNo() + " with amount $" + transferRequest.getAmount());
	}

	@Override
	public boolean checkIfValid(Transaction transaction) {
		if(bankRepository.findValidBank(transaction.getSenderDetails()) != null) 
			return true;
		return false;
	}
}
