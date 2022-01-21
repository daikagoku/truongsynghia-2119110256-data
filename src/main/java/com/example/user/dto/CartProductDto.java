package com.example.user.dto;

public class CartProductDto extends ProductDto{
	private long quantity;
	
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
