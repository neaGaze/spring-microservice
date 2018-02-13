package com.stargate.ach.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.stargate.ach.business.entity.BLTransaction;

@Component
public class BLTransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		if (aClass.equals(BLTransaction.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object obj, Errors err) {
		BLTransaction blTransaction = (BLTransaction) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "transactionId", "Invalid transactionID defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "senderDetails", "Invalid senderDetails defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "receiverDetails", "Invalid receiverDetails defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "amount", "Invalid amount defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "status", "Invalid status defined");
		
		if(checkNumber(blTransaction.getAmount().toString()))
			err.rejectValue("amount", "Invalid Number. Parse Exception");
	}

	private boolean checkNumber(String amount) {
		try {
		Double.parseDouble(amount);
		} catch(NumberFormatException  ex) {
			return true;
		}
		return false;
	}
}
