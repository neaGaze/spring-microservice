package com.stargate.transferfund.entity;

import java.io.Serializable;

import javax.persistence.*;

public class Account implements Serializable {

	private String name;
	
	private Long accountNumber;
	
	private Long rountingNumber;
	
	private AccountType accountType;
	
	private String bankName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getRountingNumber() {
		return rountingNumber;
	}

	public void setRountingNumber(Long rountingNumber) {
		this.rountingNumber = rountingNumber;
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
