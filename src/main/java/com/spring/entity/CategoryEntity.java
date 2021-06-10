package com.spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
	
	@NotEmpty(message = "\"Tên loại sản phẩm\" không được để trống.")
	@Size(min = 2, max = 50, message = "\"Tên loại sản phẩm\" nên có độ dài từ 2 đến 50 ký tự.")
	@Pattern(regexp = "[\\w ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ,.]*", message = "Không được chứa ký tự đặc biệt")
	@Column
	private String name;
	
	@Column(columnDefinition = "boolean default false", name = "is_accessory")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isAccessory = false;
	
	@OneToMany(mappedBy = "category")
	private List<BrandEntity> listBrand;
	
	@OneToMany(mappedBy = "category")
	private List<FilterEntity> listFilter;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccessory() {
		return isAccessory;
	}

	public void setAccessory(boolean isAccessory) {
		this.isAccessory = isAccessory;
	}

	public List<BrandEntity> getListBrand() {
		return listBrand;
	}

	public void setListBrand(List<BrandEntity> listBrand) {
		this.listBrand = listBrand;
	}

	public List<FilterEntity> getListFilter() {
		return listFilter;
	}

	public void setListFilter(List<FilterEntity> listFilter) {
		this.listFilter = listFilter;
	}

	
	
}
