package com.spring.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.FilterDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IFilterProductService;

@RestController
public class FilterProductAPI {
	@Autowired
	private IFilterProductService service;
	
    @GetMapping(value = "/api/categories/{id}/filters")
    @ResponseBody
    public List<FilterDTO> getAllByCategoryId(@PathVariable Long id) {
        
        return service.findAllByCategory(id);
    }
    
    @GetMapping(value = "/api/filters/{id}")
    @ResponseBody
    public FilterDTO getOneByCategoryId(@PathVariable Long id) {
        return service.findOneById(id);
    }
    
    @PostMapping("/api/filters")
	public MessageAlertModel createFilter(@RequestBody FilterDTO dto) {
		return service.save(dto);
	}
    
    @PutMapping("/api/filters")
	public MessageAlertModel updateFilter(@RequestBody List<FilterDTO> list) {
		return service.update(list);
	}
    
    @PutMapping("/api/filters/{id}")
	public MessageAlertModel updateFilter(@PathVariable Long id, @RequestBody FilterDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
    
    @DeleteMapping("/api/filters/{ids}")
	public MessageAlertModel deleteFilter(@PathVariable Long[] ids) {
		return service.delete(ids);
	}
    

    
    
}
