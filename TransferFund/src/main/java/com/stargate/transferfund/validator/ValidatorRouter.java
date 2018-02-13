package com.stargate.transferfund.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stargate.transferfund.business.entity.BLTransferRequest;
import com.stargate.transferfund.entity.Transaction;

@Configuration
public class ValidatorRouter implements Validator {

	@Autowired
	private TransferRequestValidator transferRequestValidator;
	
	@Autowired
	private TransactionValidator transactionValidator;
	
	private ValidatorRouter correctValidator;

	@Override
	public boolean supports(Class<?> aClass) {
		if((BLTransferRequest.class).equals(aClass))
			correctValidator = transferRequestValidator;

		if((Transaction.class).equals(aClass))
			correctValidator = transactionValidator;
		
		return correctValidator.supports(aClass);
	}

	@Override
	public void validate(Object obj, Errors err) {
		correctValidator.validate(obj, err);
	}
}
