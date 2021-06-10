package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

	@NotEmpty(message = "\"Tên Sản Phẩm\" không được để trống.")
	@Size(min = 2, max = 50, message = "\"Tên Sản Phẩm\" nên có độ dài từ 2 đến 50 ký tự.")
	@Pattern(regexp = "[\\w ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ ,.\\-:+/)(&]*", message = "\"Tên Sản Phẩm\" không được chứa ký tự đặc biệt")
	@Column
	private String name;
	
	@Column
	private int price;

	@Column
	private int sale;

	@Column
	private int qty;
	
	@Column(name = "url_image")
	private String urlImage;
	
	@Column(name = "url_images", columnDefinition = "TEXT")
	private String urlImages;

	@Column(columnDefinition = "boolean default false", name = "is_new")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isNew = false;

	@Column(columnDefinition = "boolean default false", name = "is_hot")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isHot = false;

	@Column
	private long views;

	@Column
	private int star;

	@Column(columnDefinition = "TEXT")
	private String promotions;

	@Column(columnDefinition = "TEXT")
	private String article;

	@Column(columnDefinition = "TEXT")
	private String specifications;
	
	@Column
	private String tags;
	
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;

	@ManyToMany
	@JoinTable(name = "filter_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "filter_id"))
	private List<FilterEntity> listFilter = new ArrayList<FilterEntity>();

	
	@OneToMany(mappedBy = "product")
	private List<ReviewEntity> listReview;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}

	public List<FilterEntity> getListFilter() {
		return listFilter;
	}

	public void setListFilter(List<FilterEntity> listFilter) {
		this.listFilter = listFilter;
	}

	public List<ReviewEntity> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewEntity> listReview) {
		this.listReview = listReview;
	}
}
