package com.daofab.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChildResultDTO {
	Long id;
	String sender;
	String receiver;
	int totalAmount;
	int PaidAmount;
	Long parentId;
	@Override
	public String toString() {
		return "ChildResultDTO [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", totalAmount="
				+ totalAmount + ", PaidAmount=" + PaidAmount + ", parentId=" + parentId + "]";
	}
}
