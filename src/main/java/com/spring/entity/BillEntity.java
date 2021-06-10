package com.spring.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity {
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column
	private String email;
	
	@Column
	private String phone;
	
	@Column
	private String gender;
	
	@Column
	private String address;
	
	@Column
	private String note;
	
	@Column(columnDefinition = "boolean default false", name = "is_payment")
	private boolean isPayment;
	
	@Column(name = "total_money")
	private int totalMoney;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(mappedBy = "bill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BillDetailEntity> listBillDetail;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isPayment() {
		return isPayment;
	}

	public void setPayment(boolean isPayment) {
		this.isPayment = isPayment;
	}

	
	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<BillDetailEntity> getListBillDetail() {
		return listBillDetail;
	}

	public void setListBillDetail(List<BillDetailEntity> listBillDetail) {
		this.listBillDetail = listBillDetail;
	}
	
}
