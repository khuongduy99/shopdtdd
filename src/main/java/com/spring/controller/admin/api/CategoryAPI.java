package com.spring.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.CategoryDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.ICategoryService;

@RestController
public class CategoryAPI {
	@Autowired
	private ICategoryService service;
	
    @GetMapping(value = "/api/categories")
    @ResponseBody
    public List<CategoryDTO> getAllByIsAccessory(@RequestParam boolean isAccessory) {
        List<CategoryDTO> list = service.findAllByIsAccessory(isAccessory);
        return list;
    }
    
    @GetMapping(value = "/api/categories/{id}")
    @ResponseBody
    public CategoryDTO getOneById(@PathVariable Long id) {
        return service.findOneById(id);
    }
    
    @PostMapping("/api/categories")
	public MessageAlertModel createStatusType(@RequestBody CategoryDTO dto) {
		return service.save(dto);
	}
    
    @PutMapping("/api/categories/{id}")
	public MessageAlertModel updateStatusType(@PathVariable Long id, @RequestBody CategoryDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
    
    @DeleteMapping("/api/categories/{ids}")
	public MessageAlertModel deleteStatusType(@PathVariable Long[] ids) {
		return service.delete(ids);
	}
}
