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

import com.spring.dto.RoleDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IRoleService;

@RestController
public class RoleAPI {
	@Autowired
	private IRoleService service;
	
    @GetMapping(value = "/api/roles")
    @ResponseBody
    public List<RoleDTO> getAll() {
        List<RoleDTO> list = service.findAll();
        return list;
    }
    
    @GetMapping(value = "/api/roles/{id}")
    @ResponseBody
    public RoleDTO getOneById(@PathVariable Long id) {
        return service.findOneById(id);
    }
    
    @PostMapping("/api/roles")
	public MessageAlertModel createStatusType(@RequestBody RoleDTO dto) {
		return service.save(dto);
	}
    
    @PutMapping("/api/roles/{id}")
	public MessageAlertModel updateStatusType(@PathVariable Long id, @RequestBody RoleDTO dto) {
    	dto.setId(id);
		return service.update(dto);
	}
    
    @DeleteMapping("/api/roles/{ids}")
	public MessageAlertModel deleteStatusType(@PathVariable Long[] ids) {
		return service.delete(ids);
	}
}
