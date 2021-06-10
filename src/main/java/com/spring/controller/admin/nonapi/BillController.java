package com.spring.controller.admin.nonapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.utils.StatusCustom;

@Controller(value = "billControllerOfadmin")
public class BillController {
	@RequestMapping(value = { "/admin-page/bill" }, method = RequestMethod.GET)
	public String getCategoryPage(@RequestParam(value = "status", required = true) String status, Model model) {
		if(status.equalsIgnoreCase("CONFIRMED")) status = StatusCustom.CONFIRMED;
		if(status.equalsIgnoreCase("PROCESSING")) status = StatusCustom.PROCESSING;
		if(status.equalsIgnoreCase("SHIPPING")) status = StatusCustom.SHIPPING;
		if(status.equalsIgnoreCase("DELIVERED")) status = StatusCustom.DELIVERED;
		if(status.equalsIgnoreCase("CANCELLED")) status = StatusCustom.CANCELLED;
		model.addAttribute("status", status);
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_BILL());
		return "admin/bill";
	}
}
