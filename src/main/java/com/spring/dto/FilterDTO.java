package com.spring.dto;

import java.util.ArrayList;
import java.util.List;

public class FilterDTO extends AbstractDTO{
	private long categoryId;
	
	private Long parentId;
	
	private List<FilterDTO> children;
	
	

	public List<FilterDTO> getChildren() {
		return children;
	}

	public void addChildren(FilterDTO c) {
		if(children == null )  this.children = new ArrayList<FilterDTO>();
		this.children.add(c);
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

}
