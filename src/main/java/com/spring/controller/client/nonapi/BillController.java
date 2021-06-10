package com.spring.controller.client.nonapi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BillDTO;
import com.spring.dto.BillDetailDTO;
import com.spring.model.Cart;
import com.spring.model.ProductModel;
import com.spring.model.UserModel;
import com.spring.service.IBillService;
import com.spring.utils.SessionUtil;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Controller
public class BillController {
	
	@Autowired
	private IBillService billService;
	
	@RequestMapping(value = {"/dat-hang"}, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String order(@RequestParam String fullName, @RequestParam(required = false) String gender, @RequestParam String address, @RequestParam(required = false) String note, 
			@RequestParam String email, @RequestParam Long userId, @RequestParam boolean isPayment, @RequestParam String phone,
			HttpServletRequest request, Model model) {
		
		BillDTO billDTO = new BillDTO();
		billDTO.setFullName(fullName);
		billDTO.setAddress(address);
		billDTO.setGender(gender);
		billDTO.setEmail(email);
		billDTO.setNote(note);
		billDTO.setPhone(phone);
		billDTO.setPayment(isPayment);
		Cart cart = (Cart) SessionUtil.getInstance().getValue(request, "Cart");
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "UserModel");
		if(cart != null) {
			List<BillDetailDTO> listBill = new ArrayList<BillDetailDTO>();
			for(ProductModel product : cart.getList()) {
				BillDetailDTO billDetail = new BillDetailDTO();
				billDetail.setNameProduct(product.getName());
				billDetail.setPrice(product.getPrice());
				billDetail.setQty(product.getQtyInCart());
				billDetail.setUrlImage(product.getUrlImage());
				billDetail.setPromotions(product.getPromotions());
				billDetail.setIdProduct(product.getId());
				listBill.add(billDetail);
			}
			billDTO.setListBillDetail(listBill);
			billDTO.setTotalMoney(cart.getTotal());
			billDTO.setUserId(user.getId());
			
		} else {
			
			return "redirect:/xem-gio-hang";
		}
		// neu da thanh toan
		if(isPayment) {
			String code = "EL-";
			code += ("" + new Date().getTime()).substring(9);
			Random random = new Random();
			code += String.format("%04d", random.nextInt(10000));
			billDTO.setCode(code);
			billService.save(billDTO);
			SessionUtil.getInstance().removeValue(request, "Cart");
			return "client/success-order";
		} else {
			// neu chua da thanh toan
			String code = "EL-";
			code += ("" + new Date().getTime()).substring(9);
			Random random = new Random();
			code += String.format("%04d", random.nextInt(10000));
			billDTO.setCode(code);
			String codeConfirm = String.format("%04d", random.nextInt(10000));
			billDTO.setCodeConfirmOrder(codeConfirm);
			
			
			Calendar date = Calendar.getInstance();
			long timeInSecs = date.getTimeInMillis();
			Date afterAdding5Mins = new Date(timeInSecs + (5 * 60 * 1000));
			
			billDTO.setDateExpired(afterAdding5Mins);
			SessionUtil.getInstance().putValue(request, "Bill", billDTO);
			WebUtils.sendEmail(billDTO.getEmail(), "YÊU CẦU XÁC THỰC ĐƠN ĐẶT HÀNG", "<h4>Code xác thực:</h4><h2>"+codeConfirm+"</h2>");
		}
		
		
		return "client/confirm-order";
	}
	
	@RequestMapping(value = {"/dat-hang"}, method = RequestMethod.GET)
	public String order() {
		
		return "redirect:/";
	}
	
	@RequestMapping(value = {"/xac-nhan-dat-hang"}, method = RequestMethod.GET)
	public String confirmOrder() {
		
		return "redirect:/";
	}

	@RequestMapping(value = {"/xac-nhan-dat-hang"}, method = RequestMethod.POST)
	public String confirmOrder(@RequestParam String code, @RequestParam boolean isExpired,
			HttpServletRequest request, Model model) {
		if(isExpired) {
			SessionUtil.getInstance().removeValue(request, "Bill");
			return "redirect:/";
		}
		BillDTO bill = (BillDTO) SessionUtil.getInstance().getValue(request, "Bill");
		if(bill != null ) {
			if(bill.getDateExpired().getTime() < new Date().getTime()) {
				SessionUtil.getInstance().removeValue(request, "Bill");
				return "redirect:/";
			}
			if(code.equals(bill.getCodeConfirmOrder())) {
				billService.save(bill);
				SessionUtil.getInstance().removeValue(request, "Bill");
				SessionUtil.getInstance().removeValue(request, "Cart");
				return "client/success-order";
			} else {
				model.addAttribute("error", "Mã xác nhận không đúng. Thử lại");
				return "client/confirm-order";
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = {"/xem-don-hang"}, method = RequestMethod.GET)
	public String viewOrder(HttpServletRequest request, @RequestParam(required = false) String code, Model model) {
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "UserModel");
		if(user == null) return "redirect:/";
		if(code != null) {
			model.addAttribute("BillDTO", billService.findOneByCode(code));
			return "client/bill-detail";
		} else {
			model.addAttribute("ListBill", billService.findAllByUserId(user.getId()));
			model.addAttribute("ListBillConfirmed", billService.findAllByUserIdAndStatus(user.getId(), StatusCustom.CONFIRMED));
			model.addAttribute("ListBillProcessing", billService.findAllByUserIdAndStatus(user.getId(), StatusCustom.PROCESSING));
			model.addAttribute("ListBillShipping", billService.findAllByUserIdAndStatus(user.getId(), StatusCustom.SHIPPING));
			model.addAttribute("ListBillDelivered", billService.findAllByUserIdAndStatus(user.getId(), StatusCustom.DELIVERED));
			model.addAttribute("ListBillCancelled", billService.findAllByUserIdAndStatus(user.getId(), StatusCustom.CANCELLED));
			return "client/bill";
		}
		
		
		
	}
	
}
