package com.stargate.transferfund.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bank_id")
	private Integer bankId;
	
	private Integer customerId;
	
	private String bankName;
	
	private String accountNo;
	
	private String routingNo;
	
	private Double availableBalance;
	
	private String accountType;
	
	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


}
