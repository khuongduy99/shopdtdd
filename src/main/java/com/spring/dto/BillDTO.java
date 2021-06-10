package com.spring.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class BillDTO extends AbstractDTO{
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@NotEmpty(message = "Họ tên không được để trống.")
	private String fullName;
	
	@NotEmpty(message = "Email không được để trống.")
	@Email(message = "Email không hợp lệ.")
	private String email;
	
	@NotEmpty(message = "Vui lòng cung cấp số điện thoại để chúng tôi có thể liên lạc.")
	private String phone;
	
	private String gender;
	
	@NotEmpty(message = "Vui lòng cung cấp địa chỉ để chúng tôi có thể giao hàng đúng nơi.")
	private String address;
	
	private String note;
	
	private String code;
	
	private String codeConfirmOrder;
	
	private Date dateExpired;
	
	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeConfirmOrder() {
		return codeConfirmOrder;
	}

	public void setCodeConfirmOrder(String codeConfirmOrder) {
		this.codeConfirmOrder = codeConfirmOrder;
	}

	private boolean isPayment;
	
	private int totalMoney;
	
	private List<BillDetailDTO> listBillDetail;

	public List<BillDetailDTO> getListBillDetail() {
		return listBillDetail;
	}

	public void setListBillDetail(List<BillDetailDTO> listBillDetail) {
		this.listBillDetail = listBillDetail;
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

	
}
