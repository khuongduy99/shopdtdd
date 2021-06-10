package com.spring.model;

public class FileImage {
	private int id;
	private String name;
	private String url;
	private boolean isFolder;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getIsFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public FileImage(int id, String name, String url, boolean isFolder) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.isFolder = isFolder;
	}
}
