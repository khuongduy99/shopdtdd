package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class ReviewEntity extends BaseEntity {
	@Column
	private String name;
	
	@Column
	private String comment;
	
	@Column
	private int star;

	@ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
	
	@ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
