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

import com.spring.dto.BrandDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IBrandService;

@RestController
public class BrandAPI {
	@Autowired
	private IBrandService service;
	
    @GetMapping(value = "/api/categories/{id}/brands")
    @ResponseBody
    public List<BrandDTO> getAllByCategoryId(@PathVariable Long id) {
        List<BrandDTO> list = service.findAllByCategory(id);
        return list;
    }
    
    @GetMapping(value = "/api/brands/{id}")
    @ResponseBody
    public BrandDTO getOneByCategoryId(@PathVariable Long id) {
        return service.findOneById(id);
    }
    
    @PostMapping("/api/brands")
	public MessageAlertModel createBrand(@RequestBody BrandDTO dto) {
		return service.save(dto);
	}
    
    @PutMapping("/api/brands/{id}")
	public MessageAlertModel updateBrand(@PathVariable Long id, @RequestBody BrandDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
    
    @DeleteMapping("/api/brands/{ids}")
	public MessageAlertModel deleteBrand(@PathVariable Long[] ids) {
		return service.delete(ids);
	}
    

    
    
}
