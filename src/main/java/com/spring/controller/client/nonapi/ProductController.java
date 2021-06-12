package com.spring.controller.client.nonapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BrandDTO;
import com.spring.dto.CategoryDTO;
import com.spring.dto.FilterDTO;
import com.spring.dto.ProductDTO;
import com.spring.service.IBrandService;
import com.spring.service.ICategoryService;
import com.spring.service.IFilterProductService;
import com.spring.service.IProductService;
import com.spring.utils.StatusCustom;

@Controller(value = "productControllerClient")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private IFilterProductService filterService;
	
	@RequestMapping(value = {"/nhomsp"}, method = RequestMethod.GET)
	public String getStorePage(@RequestParam(value = "n") String name, Model model) {
		CategoryDTO categoryDTO = categoryService.findOneByAlias(name);
		if(categoryDTO != null) {
			model.addAttribute("ListBrand", brandService.findAllByCategoryAliasAndStatus(name, StatusCustom.ACTIVE, StatusCustom.ACTIVE));
			int limit = 6;
			int start = 0;
			List<ProductDTO> listProduct = productService.findAllByCategoryAliasAndStatusAndLimitAndOffset(name, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, start);
			List<FilterDTO> listFilter = filterService.findAllByCategory(name);
			model.addAttribute("ListProduct", listProduct);
			model.addAttribute("ListFilter", listFilter);
			model.addAttribute("Category", categoryDTO);
			model.addAttribute("limit", limit);
			int totalItem = productService.countByCategoryAliasAndStatus(name, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
			model.addAttribute("totalItem", totalItem);
			model.addAttribute("totalPage", (int) Math.ceil((double) totalItem / limit));
		} else {
			return "404-page";
		}
		
		return "client/store";
	}
	
	@RequestMapping(value = {"/sp"}, method = RequestMethod.GET)
	public String getProductPage(@RequestParam(value = "n") String name, Model model) {
		ProductDTO productDTO = productService.findOneByAlias(name);
		if(productDTO != null) {
			BrandDTO brandDTO = brandService.findOneById(productDTO.getBrandId());
			CategoryDTO categoryDTO = categoryService.findOneById(brandDTO.getCategoryId());
			model.addAttribute("Category", categoryDTO);
			//model.addAttribute("Category", categoryDTO);
//			int limit = 3;
//			int start = 0;
//			List<ProductDTO> listProduct = productService.findAllByCategoryAliasAndStatusAndLimitAndOffset(name, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, start);
//			List<FilterDTO> listFilter = filterService.findAllByCategory(name);
//			model.addAttribute("ListProduct", listProduct);
//			model.addAttribute("ListFilter", listFilter);
//			model.addAttribute("Category", categoryDTO);
//			model.addAttribute("limit", limit);
//			int totalItem = productService.countByCategoryAliasAndStatus(name, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
//			model.addAttribute("totalItem", totalItem);
			model.addAttribute("Product", productDTO);
		} else {
			return "404-page";
		}
		
		return "client/product";
	}
	
	@RequestMapping(value = {"/timkiem"}, method = RequestMethod.GET)
	public String searchProduct(@RequestParam(value = "n") String keyword, Model model) {
		int limit = 8;
		int offset = 0;
		model.addAttribute("limit", limit);
		int totalItem = productService.countSearch(keyword, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("totalPage", (int) Math.ceil((double) totalItem / limit));
		List<ProductDTO> listProduct = productService.searchByKeywordAndStatusAndLimitAndOffset(keyword, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
		model.addAttribute("ListProduct", listProduct);
		model.addAttribute("keyword", keyword);
		return "client/search";
	}
	
	@RequestMapping(value = {"/tags"}, method = RequestMethod.GET)
	public String searchProductByTag(@RequestParam(value = "n") String keyword, Model model) {
		int limit = 8;
		int offset = 0;
		model.addAttribute("limit", limit);
		int totalItem = productService.countSearchByTags(keyword, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
		model.addAttribute("totalItem", totalItem);
		model.addAttribute("totalPage", (int) Math.ceil((double) totalItem / limit));
		List<ProductDTO> listProduct = productService.searchByTagsAndStatusAndLimitAndOffset(keyword, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
		model.addAttribute("ListProduct", listProduct);
		model.addAttribute("tags", keyword);
		return "client/search";
	}
}
