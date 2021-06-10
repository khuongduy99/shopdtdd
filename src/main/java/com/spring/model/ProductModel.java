package com.spring.model;

public class ProductModel {
	// product
	
	private Long id;
	
	private String name, urlImage, promotions;
	
	public String getPromotions() {
		return promotions;
	}
	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}
	private int totalMoney;
	
	
	
	
	public int getTotalMoneyOfProduct() {
		
		return sale != 0 ? qtyInCart * sale : qtyInCart * price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private int price;
	private int sale;
	private int qtyInCart;
	public int getPrice() {
		return sale != 0 ? sale : price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	

	// getter and seter

	public void setQtyInCart(int qtyInCart) {
		this.qtyInCart = qtyInCart;
	}
	public void updateQtyInCart(int qty) {
		this.qtyInCart += qty;
	}

	public void updateQtyInCart() {
		this.qtyInCart++;
	}

	public int getQtyInCart() {
		if (qtyInCart < 0)
			qtyInCart = 1;
		if (qtyInCart > 3)
			qtyInCart = 3;
		return qtyInCart;
	}


}
