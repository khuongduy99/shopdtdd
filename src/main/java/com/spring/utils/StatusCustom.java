package com.spring.utils;

public class StatusCustom {
	public static final String ACTIVE = "Hoạt Động";
	public static final String IN_ACTIVE = "Không Hoạt Động";
	public static final String BLOCK = "Bị Khóa";
	
	public static final String OUT_OF_STOCK = "Hết hàng";
	
	public static final String CONFIRMED = "Đã xác nhận";
	public static final String PROCESSING = "Đang xử lý";
	public static final String SHIPPING = "Đang giao";
	public static final String DELIVERED = "Đã giao hàng";
	public static final String CANCELLED = "Đã hủy";
	
	public static String[] LIST_STATUS_OF_CATEGORY(){
		String [] res = {ACTIVE, IN_ACTIVE};
		return res;
	}
	
	public static String[] LIST_STATUS_OF_USER(){
		String [] res = {ACTIVE, BLOCK};
		return res;
	}
	
	public static String[] LIST_STATUS_OF_PRODUCT(){
		String [] res = {ACTIVE, IN_ACTIVE, OUT_OF_STOCK};
		return res;
	}
	
	public static String[] LIST_STATUS_OF_BILL(){
		String [] res = {CONFIRMED, PROCESSING, SHIPPING, DELIVERED, CANCELLED};
		return res;
	}
	
}


