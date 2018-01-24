package com.stargate.entity;
import javax.persistence.Entity;

@Entity // This tells Hibernate to make a table out of this class
public class Balance {

    private Double availableBalance;

	public Double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}
   
}
