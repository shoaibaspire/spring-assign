package com.daofab.model;

import org.springframework.stereotype.Component;

@Component
public class Child {

	Long id;
	Long parentId;
	int paidAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	@Override
	public String toString() {
		return "Child [id=" + id + ", parentId=" + parentId + ", paidAmount=" + paidAmount + "]";
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	} 
}
