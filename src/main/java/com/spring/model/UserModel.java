package com.spring.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserModel extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserModel(String email, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(email, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	private String fullName, address, gender, phone, email, userName, urlPageRedirectLoginSuccess;
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	private Long id;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFullName() {
		return fullName;
	}
	

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public String getUrlPageRedirectLoginSuccess() {
		return urlPageRedirectLoginSuccess;
	}


	public void setUrlPageRedirectLoginSuccess(String urlPageRedirectLoginSuccess) {
		this.urlPageRedirectLoginSuccess = urlPageRedirectLoginSuccess;
	}
	
	
	
}
