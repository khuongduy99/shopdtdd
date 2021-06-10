package com.spring.controller.admin.nonapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.CategoryDTO;
import com.spring.service.IBillService;
import com.spring.service.ICategoryService;
import com.spring.service.IProductService;
import com.spring.utils.WebUtils;

@Controller
public class AdminMainController {
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBillService billService;
	
	@RequestMapping(value = { "/admin-page"}, method = RequestMethod.GET)
    public String adminHome(Model model) {
		List<CategoryDTO> listTotalProductOfCategory = categoryService.findAll();
		String dataOfTotalProductOfCategory = "[";
		String colorOfTotalProductOfCategory ="[";
		String[] getAllColor = WebUtils.getAllCodeColor();
		for(int i = 0; i < listTotalProductOfCategory.size(); i++) {
			CategoryDTO category = listTotalProductOfCategory.get(i);
			dataOfTotalProductOfCategory += "{";
			dataOfTotalProductOfCategory += "label: " + " '"+category.getName()+"', ";
			dataOfTotalProductOfCategory += "value: " + productService.countByCategoryId(category.getId());
			
			if(i == listTotalProductOfCategory.size() - 1) {
				colorOfTotalProductOfCategory += "'"+getAllColor[i]+"'";
				dataOfTotalProductOfCategory += "}";
			} else {
				colorOfTotalProductOfCategory += "'"+getAllColor[i]+"',";
				dataOfTotalProductOfCategory += "},";
			}
			
		}
		colorOfTotalProductOfCategory += "]";
		dataOfTotalProductOfCategory += "]";
		
            
		String donutChar = "Morris.Donut({element: 'donut_chart',data:" + dataOfTotalProductOfCategory + ",colors:" + colorOfTotalProductOfCategory;
		donutChar += ", formatter: function (y) { return y + '%' }});";
		List<Integer> totalMoneyOfMonthYear = new ArrayList<Integer>();
		for(int i = 1; i < 13; i++) {
			try {
				totalMoneyOfMonthYear.add(billService.sumMoneyByYearAndMonth(2021, i));
			} catch (Exception e) {
				totalMoneyOfMonthYear.add(0);
			}
			
		}
		model.addAttribute("DonutChart", donutChar);
        return "admin/index";
    }
}
