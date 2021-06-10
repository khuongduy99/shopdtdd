package com.spring.dto;

public class BillDetailDTO extends AbstractDTO{
	private Long idProduct;
	
	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	private String nameProduct;
	
	
	private String urlImage;
	
	
	private int price;
	
	private int qty;
	
	private String promotions;

	private Long billId;

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getPromotions() {
		return promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}
	
	
}
