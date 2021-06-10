package com.spring.controller.admin.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.StatisticalModel;
import com.spring.service.IBillService;
import com.spring.service.IUserService;
import com.spring.utils.StatusCustom;

@RestController
public class StatisticalAPI {
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value = "/api/statistical")
    @ResponseBody
    public List<StatisticalModel> getStatistical(@RequestParam String type, @RequestParam int year, @RequestParam(required = false) Integer month) {
    	
    		List<StatisticalModel> listStatisticalBill = new ArrayList<StatisticalModel>();
    		if(month == null) {
    			if(type.equals("statusBill")) {
    				StatisticalModel modelSuccessBill = new StatisticalModel();
    				modelSuccessBill.setLabel("Đã giao");
    				modelSuccessBill.setData(billService.countByYearAndStatus(year, StatusCustom.DELIVERED));
    				StatisticalModel modelCancelBill = new StatisticalModel();
    				modelCancelBill.setLabel("Đã hủy");
    				modelCancelBill.setData(billService.countByYearAndStatus(year, StatusCustom.CANCELLED));
    				
    				listStatisticalBill.add(modelSuccessBill);
    				listStatisticalBill.add(modelCancelBill);
    				return listStatisticalBill;
				}
    			
    			if(type.equals("roleUser")) {
    				StatisticalModel modelRoleAdmin = new StatisticalModel();
    				modelRoleAdmin.setLabel("ADMIN");
    				modelRoleAdmin.setData(userService.countByRoleId(2L));
    				StatisticalModel modelRoleUser = new StatisticalModel();
    				modelRoleUser.setLabel("USER");
    				modelRoleUser.setData(userService.countByRoleId(1L));
    				
    				listStatisticalBill.add(modelRoleAdmin);
    				listStatisticalBill.add(modelRoleUser);
    				return listStatisticalBill;
				}
    			
	    			for(int i = 1; i < 13; i++) {
		    			StatisticalModel model = new StatisticalModel();
		    			model.setLabel("Tháng " + i);
		    			try {
		    				if(type.equals("bill")) {
		    					model.setData(billService.sumMoneyByYearAndMonth(year, i));
		    				}
		    				if(type.equals("user")) {
		    					model.setData(userService.countByYearAndMonth(year, i));
		    				} 
		    				
		    				 
		    				
		    			} catch (Exception e) {
		    				model.setData(0);
		    			}
		    			listStatisticalBill.add(model);
	    			}
    			
    		} else {
    			if(type.equals("statusBill")) {
    				StatisticalModel modelSuccessBill = new StatisticalModel();
    				modelSuccessBill.setLabel("Đã giao");
    				modelSuccessBill.setData(billService.countByYearAndMonthAndStatus(year, month,  StatusCustom.DELIVERED));
    				StatisticalModel modelCancelBill = new StatisticalModel();
    				modelCancelBill.setLabel("Đã hủy");
    				modelCancelBill.setData(billService.countByYearAndMonthAndStatus(year, month, StatusCustom.CANCELLED));
    				
    				listStatisticalBill.add(modelSuccessBill);
    				listStatisticalBill.add(modelCancelBill);
    				return listStatisticalBill;
				}
    			
    			for(Integer i : dates(year, month)) {
    				StatisticalModel model = new StatisticalModel();
	    			model.setLabel("Ngày " + i);
	    			try {
	    				if(type.equals("bill")) {
	    					model.setData(billService.sumMoneyByYearAndMonthAndDay(year, month, i));
	    				}
	    				if(type.equals("user")) {
	    					model.setData(userService.countByYearAndMonthAndDay(year, month, i));
	    				}
	    			} catch (Exception e) {
	    				model.setData(0);
	    			}
	    			listStatisticalBill.add(model);
    			}
    		
    			
    	}
    	
    		return listStatisticalBill;
    }
	
	public static List<Integer> dates(int year, int month) {
		Date date = new Date(year, month - 1, 3);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		int myMonth=cal.get(Calendar.MONTH);
		List<Integer> d = new ArrayList<Integer>();
		while (myMonth==cal.get(Calendar.MONTH)) {
			d.add(cal.getTime().getDate());
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return d;
	}

}
