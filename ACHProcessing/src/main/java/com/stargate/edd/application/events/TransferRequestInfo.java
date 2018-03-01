package com.stargate.edd.application.events;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransferRequestInfo implements Serializable {
	
	
	private static final long serialVersionUID = -2393938903016528933L;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private UUID id;
	private String from;
	private String destination;
	private Double amount;
	
	public TransferRequestInfo() {}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TransferRequestInfo [id=" + id + ", from=" + from + ", destination=" + destination + ", amount="
				+ amount + "]";
	}
}
