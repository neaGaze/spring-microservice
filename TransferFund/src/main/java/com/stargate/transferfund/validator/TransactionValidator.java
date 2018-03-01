package com.stargate.transferfund.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import com.stargate.transferfund.entity.Account;
import com.stargate.transferfund.entity.Transaction;

@Component
public class TransactionValidator extends ValidatorRouter {

	@Override
	public boolean supports(Class<?> aClass) {
		if (aClass.equals(Transaction.class)) {
			return true;
		}

		return false;
	}

	@Override
	public void validate(Object obj, Errors err) {
		Transaction transaction = (Transaction) obj;
		// ValidationUtils

		Account senderDetails = transaction.getSenderDetails();
		Account receiverDetails = transaction.getReceiverDetails();

		if (checkInputString(receiverDetails))
			err.rejectValue("receiverDetails", "Invalid field `receiverDetails");

		if (checkInputString(senderDetails))
			err.rejectValue("senderDetails", "Invalid field `senderDetails");

		if (checkNumber(transaction.getAmount().toString()))
			err.rejectValue("amount", "Invalid field `amount`");

		if (senderDetails != null) {
			if (checkInputString(senderDetails.getAccountNumber()))
				err.rejectValue("amount", "Invalid field `accountNumber`");

			if (checkInputString(senderDetails.getBankName()))
				err.rejectValue("amount", "Invalid field `bankName`");

			if (checkInputString(senderDetails.getRoutingNumber()))
				err.rejectValue("amount", "Invalid field `routingNumber`");

			if (checkInputString(senderDetails.getAccountType().toString()))
				err.rejectValue("amount", "Invalid field `accountType`");

			if (checkNumber(senderDetails.getBankId().toString()))
				err.rejectValue("amount", "Invalid field `bankId`");
		}
		
		if (receiverDetails != null) {
			if (checkInputString(receiverDetails.getAccountNumber()))
				err.rejectValue("amount", "Invalid field `accountNumber`");

			if (checkInputString(receiverDetails.getBankName()))
				err.rejectValue("amount", "Invalid field `bankName`");

			if (checkInputString(receiverDetails.getRoutingNumber()))
				err.rejectValue("amount", "Invalid field `routingNumber`");

			if (checkInputString(receiverDetails.getAccountType().toString()))
				err.rejectValue("amount", "Invalid field `accountType`");

			if (checkNumber(receiverDetails.getBankId().toString()))
				err.rejectValue("amount", "Invalid field `bankId`");
		}
	}

	private boolean checkInputString(String input) {
		return (input == null || input.trim().length() == 0);
	}

	private boolean checkInputString(Account account) {
		if (account == null)
			return true;

		return false;
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
