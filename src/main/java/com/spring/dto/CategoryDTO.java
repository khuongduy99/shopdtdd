package com.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO extends AbstractDTO{
	
	@JsonProperty
	private boolean isAccessory;
	
	private int totalProduct;
	
	
	public boolean isAccessory() {
		return isAccessory;
	}
	public void setAccessory(boolean isAccessory) {
		this.isAccessory = isAccessory;
	}
	public int getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	
}
