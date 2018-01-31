package com.stargate.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity// This tells Hibernate to make a table out of this class
@Table(name= "bank", schema = "ach_stargate")
@NamedNativeQuery(name = "balance", query = "select b.available_balance, b.bank_id, b.customer_id,b.bank_name, b.account_no from bank b where b.account_no=:account_no", resultClass=Bank.class) 

public class Bank {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 
	@Column(name="bank_id")

   private Integer bank_id;
   
   private Integer customer_id;
   @Column(name="account_no")
   private String account_no;
   
   private String bank_name;
   
   @Column(name="available_balance")
   private Double available_balance;

  
public Integer getBank_id() {
	return bank_id;
}

public void setBank_id(Integer bank_id) {
	this.bank_id = bank_id;
}

public Integer getCustomer_id() {
	return customer_id;
}
@OneToOne(cascade = {CascadeType.ALL})
public void setCustomer_id(Integer customer_id) {
	this.customer_id = customer_id;
}

public String getAccount_no() {
	return account_no;
}

public void setAccount_no(String account_no) {
	this.account_no = account_no;
}

public String getBank_name() {
	return bank_name;
}

public void setBank_name(String bank_name) {
	this.bank_name = bank_name;
}

public Double getAvailable_balance() {
	return available_balance;
}

public void setAvailable_balance(Double available_balance) {
	this.available_balance = available_balance;
}

}
   
 