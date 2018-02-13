package com.stargate.transferfund.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.stargate.transferfund.business.entity.BLTransferRequest;
import com.stargate.transferfund.entity.TransactionType;
import com.stargate.transferfund.entity.TransferRequest;

@Component
public class TransferRequestValidator extends ValidatorRouter {

	@Override
	public boolean supports(Class<?> aClass) {
		if (aClass.equals(BLTransferRequest.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object obj, Errors err) {
		BLTransferRequest transferRequest = (BLTransferRequest) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "accountNo", "Invalid account number defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "amount", "Invalid amount defined");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "transactionType", "Invalid transaction type defined");
		
		if(checkIfCorrectTransactionType(transferRequest.getTransactionType()))
			err.rejectValue("transactionType", "Transaction type should be either DEBIT or CREDIT");
		
		if(transferRequest != null && checkNumber(transferRequest.getAmount().toString()))
			err.rejectValue("amount", "Invalid Number. Parse Exception");
	}
	
	private boolean checkIfCorrectTransactionType(TransactionType txnType) {
		return (txnType == TransactionType.CREDIT || txnType == TransactionType.DEBIT);
	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}
	
	private boolean checkNumber(String amount) {
		if(checkInputString(amount)) return true;
		try {
		Double.parseDouble(amount);
		} catch(NumberFormatException  ex) {
			return true;
		}
		return false;
	}
}
