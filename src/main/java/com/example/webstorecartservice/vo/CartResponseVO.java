package com.example.webstorecartservice.vo;

import java.util.List;

public class CartResponseVO {

	private Integer id;

	private Integer customerId;

	private List<CartItemResponseVO> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public List<CartItemResponseVO> getItems() {
		return items;
	}

	public void setItems(List<CartItemResponseVO> items) {
		this.items = items;
	}

}
