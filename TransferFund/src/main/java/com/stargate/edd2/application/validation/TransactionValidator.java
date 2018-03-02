package com.stargate.edd2.application.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.stargate.edd.application.events.TransferRequestInfo;

@Component
public class TransactionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz.equals(TransferRequestInfo.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		TransferRequestInfo info = (TransferRequestInfo) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "from", "Invalid transactionID defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "destination", "Invalid transactionID defined");
		
		if(checkNumber(info.getAmount().toString()))
			errors.rejectValue("amount", "Invalid Number. Parse Exception");
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

