package com.stargate.status.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="transaction")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422503593095843897L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer transaction_id;
	@Transient
	private Double amount;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return transaction_id;
	}

	public void setId(Integer id) {
		this.transaction_id = id;
	}
	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}

