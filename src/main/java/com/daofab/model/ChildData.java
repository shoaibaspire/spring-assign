package com.daofab.model;


import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ChildData {
	private List<Child> data;

	public List<Child> getData() {
		return data;
	}

	public void setData(List<Child> data) {
		this.data = data;
	}
	
}
