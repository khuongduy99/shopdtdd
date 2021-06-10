package com.spring.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column
	@NotEmpty(message = "Email không được để trống.")
	@Email(message = "Email không hợp lệ.")
	private String email;
	
	@Column(name = "user_name")
	@NotEmpty(message = "Tên đăng nhập là bắt buộc.")
	private String userName;
	
	@Column
	@NotEmpty(message = "Mật khẩu là bắt buộc")
	private String password;
	
	@Column
	private String address;
	
	@Column
	private String gender;
	
	@Column
	private String phone;
	
	@Column(name = "last_login")
	private Date lastLogin;

	@OneToMany(mappedBy = "user")
	private List<ReviewEntity> listReview;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> listRole = new ArrayList<>();
	

	public List<RoleEntity> getListRole() {
		return listRole;
	}

	public void setListRole(List<RoleEntity> listRole) {
		this.listRole = listRole;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<ReviewEntity> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewEntity> listReview) {
		this.listReview = listReview;
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
	
}
