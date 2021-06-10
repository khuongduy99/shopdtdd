package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "slide")
public class SlideEntity extends BaseEntity {
	
	@Column(name = "url_image")
	private String urlImage;

	@Column(name = "url_link")
	private String urlLink;

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
	
	
}
