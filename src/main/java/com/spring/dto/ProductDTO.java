package com.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.utils.WebUtils;

public class ProductDTO extends AbstractDTO {
	private int price;

	private int sale;

	private int qty;
	
	private String urlImage;
	
	private String urlImages;
	
	@JsonProperty
	private boolean isNew = false;
	
	@JsonProperty
	private boolean isHot = false;

	private long views;

	private int star;
	
	private String tags;
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private String promotions;

	private String article;

	private String specifications;
	
	private Long brandId;

	public Long getBrandId() {
		return brandId;
	}
	
	public Long[] filtersId;

	public Long[] getFiltersId() {
		return filtersId;
	}
	
	public void setFiltersId(Long[] filtersId) {
		this.filtersId = filtersId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlImages() {
		return urlImages;
	}

	public void setUrlImages(String urlImages) {
		this.urlImages = urlImages;
	}

	public String[] getListUrlImages() {
		return urlImages.split(WebUtils.DELIMETER);
	}

	public int getPrice() {
		return price;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public long getViews() {
		return views;
	}

	public void setViews(long views) {
		this.views = views;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getPromotions() {
		return promotions;
	}

	public void setPromotions(String promotions) {
		this.promotions = promotions;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	
}
