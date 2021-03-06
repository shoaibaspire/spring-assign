package com.daofab.model;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

@Component
public class Parent extends RepresentationModel<Parent>  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950859938873784264L;
	Long id;
	String sender;
	String receiver;
	int totalAmount;
	int totalPaidAmount;
	
	public int getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(int totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "Parent [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", totalAmount=" + totalAmount
				+ ", totalPaidAmount=" + totalPaidAmount + "]";
	}
	

	
}