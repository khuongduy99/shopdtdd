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
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.ProductDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.impl.ProductService;
import com.spring.utils.StatusCustom;

@RestController
public class ProductAPI {
	
	@Autowired
	private ProductService service;
    
    @PostMapping("/api/products")
	public MessageAlertModel createNewProduct(@RequestBody ProductDTO dto) {
		return service.save(dto);
	}
    
    @GetMapping("/api/products/{id}")
    public ProductDTO getOneById(@PathVariable Long id) {
		return service.findOneById(id);
	}
   
    
    @PutMapping("/api/products/{id}")
	public MessageAlertModel updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
    
    @DeleteMapping("/api/products/{ids}")
	public MessageAlertModel deleteProduct(@PathVariable Long[] ids) {
		return service.delete(ids);
	}
    
    @GetMapping("/api/categories/{id}/products")
    public List<ProductDTO> getProductByCategoryId(@PathVariable Long id, @RequestParam int limit, @RequestParam int offset) {
		return service.findAllByCategoryIdAndStatusAndLimitAndOffset(id, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
	}
    
    @GetMapping("/api/brands/{id}/products")
    public List<ProductDTO> getProductByBrandId(@PathVariable Long id, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
    	if(limit != null && offset != null) {
    		return service.findAllByBrandIdAndStatusAndLimitAndOffset(id, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
    	} else {
    		return service.findAllByBrand(id);
    	}
		
	}
    
    @GetMapping("/api/count/brands/{id}/products")
    public int countByBrandId(@PathVariable Long id) {
		return service.countByBrandIdAndStatus(id, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
	}
    
    @GetMapping("/api/products")
    public List<ProductDTO> getProductByFilter(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long brandId, @RequestParam(required = false) String filterId, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
    	if(categoryId != null) {
    		if(filterId != null) {
    			return service.findAllByCategoryIdAndFilterAndStatusAndLimitAndOffset(categoryId, filterId,  StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
    		} else {
    			return service.findAllByCategoryIdAndStatusAndLimitAndOffset(categoryId, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
    		}
    		
    	} else {
    		if(filterId != null) {
    			return service.findAllByBrandIdAndFilterAndStatusAndLimitAndOffset(brandId, filterId,  StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
    		} else {
    			return service.findAllByBrandIdAndStatusAndLimitAndOffset(brandId, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
    		}
    		
    	}
		
	}
    
    @GetMapping("/api/count/products")
    public int countByBrandIdAndFilter(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long brandId, @RequestParam(required = false) String filterId) {

    	if(categoryId != null) {
    		if(filterId != null) {
    			return service.countByCategoryIdAndFilterAndStatus(categoryId, filterId,  StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
    		} else {
    			return service.countByCategoryIdAndStatus(categoryId, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
    		}
    		
    	} else {
    		if(filterId != null) {
    			return service.countByBrandIdAndFilterAndStatus(brandId, filterId,  StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
    		} else {
    			return service.countByBrandIdAndStatus(brandId, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE);
    		}
    		
    	}
	}
    
    @GetMapping("/api/search/products")
    public List<ProductDTO> search(@RequestParam String keyword, @RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
    	if("".equals(keyword)) return null;
    	return service.searchByKeywordAndStatusAndLimitAndOffset(keyword, StatusCustom.ACTIVE, StatusCustom.ACTIVE, StatusCustom.ACTIVE, limit, offset);
	}
}
