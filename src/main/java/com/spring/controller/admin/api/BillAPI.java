package com.spring.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.BillDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IBillService;

@RestController(value = "billApiOfAdmin")
public class BillAPI {
	@Autowired
	private IBillService service;
	
    @GetMapping(value = "/api/bill")
    @ResponseBody
    public List<BillDTO> getAllByStatus(@RequestParam String status) {
    	
        return service.findAllByStatus(status);
    }
    
    @GetMapping(value = "/api/bill/{id}")
    @ResponseBody
    public BillDTO getOneByCategoryId(@PathVariable Long id) {
        return service.findOneById(id);
    }
//    
//    @PostMapping("/api/brands")
//	public MessageAlertModel createBrand(@RequestBody BrandDTO dto) {
//		return service.save(dto);
//	}
//    
    @PutMapping("/api/bill/{id}")
	public MessageAlertModel updateBillStatus(@PathVariable Long id, @RequestBody BillDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
//    
//    @DeleteMapping("/api/brands/{ids}")
//	public MessageAlertModel deleteBrand(@PathVariable Long[] ids) {
//		return service.delete(ids);
//	}
//    

    
    
}
