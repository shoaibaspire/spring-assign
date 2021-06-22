package com.daofab.model;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ParentData {
	private List<Parent> data;

	public List<Parent> getData() {
		return data;
	}

	public void setData(List<Parent> data) {
		this.data = data;
	}
	
}