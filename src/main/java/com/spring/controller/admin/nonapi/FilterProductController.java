package com.spring.controller.admin.nonapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.CategoryDTO;
import com.spring.service.ICategoryService;

@Controller
public class FilterProductController {
	
	@Autowired
	private ICategoryService categoryService;
	
	@RequestMapping(value = { "/admin-page/filter-product"}, method = RequestMethod.GET)
    public String getFilterPage(Model model, @RequestParam(value = "category", required = true) String categoryAlias) {
		CategoryDTO categoryDTO = categoryService.findOneByAlias(categoryAlias);
		if( categoryDTO != null) {
			model.addAttribute("Category", categoryDTO);
		} else {
			return "admin/404-page";
		}
        return "admin/filter-product";
    }
}
