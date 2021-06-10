package com.spring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_detail")
public class BillDetailEntity extends BaseEntity {
	@Column(name = "product_id")
	private Long idProduct;
	
	@Column(name = "name_product")
	private String nameProduct;
	
	@Column(name = "url_image")
	private String urlImage;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "qty")
	private int qty;
	
	@Column(columnDefinition = "TEXT")
	private String promotions;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "bill_id")
	private BillEntity bill;

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getUrl_image() {
		return urlImage;
	}

	public void setUrl_image(String url_image) {
		this.urlImage = url_image;
	}

	public String getPromotions() {
		return promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public BillEntity getBill() {
		return bill;
	}

	public void setBill(BillEntity bill) {
		this.bill = bill;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
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
	
	
}
