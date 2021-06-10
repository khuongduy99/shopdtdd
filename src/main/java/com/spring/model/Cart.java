package com.spring.model;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class Cart {
	private HashMap<Long, ProductModel> data;

	public Cart() {
		this.data = new HashMap<Long, ProductModel>();
	}

	public ProductModel getProduct(Long id) {
		return data.get(id);
	}

	public int put(ProductModel item, int quantity) {
		if (data.containsKey(item.getId())) {
			data.get(item.getId()).updateQtyInCart(quantity);;
		} else {
			item.updateQtyInCart(quantity);;
			data.put(item.getId(), item);
		}
		return data.get(item.getId()).getQtyInCart();
	}
	// xoa sp
	public boolean remove(Long id) {
		return data.remove(id) == null;
	}

	// tinh tong tien
	public int getTotal() {
		int sum = 0;
		for (ProductModel p : data.values()) {
			sum += (p.getQtyInCart() * p.getPrice());
		}
		return sum;
	}
	
	public void updateTotal() {
		for (ProductModel p : data.values()) {
			p.setTotalMoney(getTotal());
		}
	}

	public Collection<ProductModel> getList() {
		return data.values();
	}
	
	public int getSize() {
		return getList().size();
	}
}
