package com.spring.controller.client.nonapi;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.service.IProductService;
import com.spring.utils.StatusCustom;

@Controller
public class HomeController {
	
	@Autowired
	private IProductService productService;
	
	
	
	@RequestMapping(value = {"/", "/trangchu"}, method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest request, Model model) {
		
		model.addAttribute("ListProductIsLaptopNew", productService.findAllByCategoryAliasAndIsNewAndStatusAndLimit("laptop", true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
		model.addAttribute("ListProductIsPhoneNew", productService.findAllByCategoryAliasAndIsNewAndStatusAndLimit("dien-thoai", true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
		model.addAttribute("ListProductIsAccessoryNew", productService.findAllByIsAccessoryAndIsNewAndStatusAndLimit(true, true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
		
		model.addAttribute("ListProductIsLaptopHot", productService.findAllByCategoryAliasAndIsHotAndStatusAndLimit("laptop", true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
		model.addAttribute("ListProductIsPhoneHot", productService.findAllByCategoryAliasAndIsHotAndStatusAndLimit("dien-thoai", true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
		model.addAttribute("ListProductIsAccessoryHot", productService.findAllByIsAccessoryAndIsHotAndStatusAndLimit(true, true, StatusCustom.ACTIVE,StatusCustom.ACTIVE, StatusCustom.ACTIVE, 10));
//		
		return "client/home";
	}
}
