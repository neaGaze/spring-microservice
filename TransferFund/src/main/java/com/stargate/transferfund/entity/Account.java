package com.stargate.transferfund.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account implements Serializable {

	private String name;

	private String accountNumber;
	
	private String routingNumber;

	private AccountType accountType;
	
	private String bankName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRountingNumber() {
		return routingNumber;
	}

	public void setRountingNumber(String rountingNumber) {
		this.routingNumber = rountingNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
