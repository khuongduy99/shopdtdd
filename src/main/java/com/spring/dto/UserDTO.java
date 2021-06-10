package com.spring.dto;

import java.sql.Timestamp;
import java.util.List;

public class UserDTO{
	
	private String email;
	
	private String fullName;
	
	private String password;
	
	private String userName;
	
	private String gender;
	
	private String phone;
	
	private String address;
	
	private List<RoleDTO> listRole;
	
	private Timestamp lastLogin;
	
	private Long[] rolesId;
	private String oldPassword;
	
	private Long id;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private String createdBy;
	private String updatedBy;
	private String status;

	public Long getId() {
		return id;
	}
	
	

	public String getOldPassword() {
		return oldPassword;
	}



	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<RoleDTO> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleDTO> listRole) {
		this.listRole = listRole;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Long[] getRolesId() {
		return rolesId;
	}

	public void setRolesId(Long[] rolesId) {
		this.rolesId = rolesId;
	}
	
	
}
