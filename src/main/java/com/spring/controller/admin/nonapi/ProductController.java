package com.spring.controller.admin.nonapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BrandDTO;
import com.spring.dto.CategoryDTO;
import com.spring.service.IBrandService;
import com.spring.service.ICategoryService;
import com.spring.service.IFilterProductService;
import com.spring.utils.StatusCustom;

@Controller
public class ProductController {

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private IFilterProductService filterProductService;
	

	@RequestMapping(value = { "/admin-page/category" }, method = RequestMethod.GET)
	public String getCategoryPage(@RequestParam(value = "isAccessory", required = true) boolean isAccessory,
			@RequestParam(value = "status", required = false) String status, @RequestParam(value = "action", required = false) String action, Model model) {
		if (action != null && action.equals("new")) {

			String scriptOpenModalNew = "<script type='text/javascript'>$('#add').modal('show');</script>";
			model.addAttribute("ScriptFormBackEnd", scriptOpenModalNew);
			
		}
		model.addAttribute("isAccessory", isAccessory);
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_CATEGORY());
		return "admin/category";
	}
	
	@RequestMapping(value = { "/admin-page/brand" }, method = RequestMethod.GET)
	public String getBrandPage(@RequestParam(value = "category", required = true) String categoryAlias, @RequestParam(value = "action", required = false) String action, Model model) {
		if (action != null && action.equals("new")) {
			String scriptOpenModalNew = "<script type='text/javascript'>$('#add').modal('show');</script>";
			model.addAttribute("ScriptFormBackEnd", scriptOpenModalNew);	
		}
		CategoryDTO categoryDTO = categoryService.findOneByAlias(categoryAlias);
		if( categoryDTO != null) {
			model.addAttribute("Category", categoryDTO);
		} else {
			return "admin/404-page";
		}
		
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_CATEGORY());
		return "admin/brand";
	}
	
	@RequestMapping(value = { "/admin-page/product" }, method = RequestMethod.GET)
	public String getProductPage(@RequestParam(value = "brand", required = true) String brandAlias, Model model) {
		
		BrandDTO brandDTO = brandService.findOneByAlias(brandAlias);
		if( brandDTO != null) {
			model.addAttribute("Category", categoryService.findOneById(brandDTO.getCategoryId()));
			model.addAttribute("Brand", brandDTO);
		} else {
			return "404-page";
		}
		
		
		model.addAttribute("ListFilter", filterProductService.findAllByCategory(brandDTO.getCategoryId()));
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_CATEGORY());
		return "admin/product";
	}
}
