package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "filter")
public class FilterEntity extends BaseEntity {
	
	@Column
	private String name;

	@ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity category;
	
	@ManyToMany(mappedBy = "listFilter")
    private List<ProductEntity> listProduct = new ArrayList<ProductEntity>();
	
	@Column(name="parent_id")
	private Long parentId;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<ProductEntity> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<ProductEntity> listProduct) {
		this.listProduct = listProduct;
	}

}
