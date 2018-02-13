package com.stargate.transferfund.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Account;

public class BankInformationExtractor {
	
	@Autowired 
	private Bank extractedBank;
	
	public void convertFromTransaction(Account account) {
		extractedBank.setAccountNo(account.getAccountNumber());
		extractedBank.setRoutingNo(account.getRoutingNumber());
		extractedBank.setBankName(account.getBankName());
	}
}
