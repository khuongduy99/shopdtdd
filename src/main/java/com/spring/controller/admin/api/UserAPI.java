package com.spring.controller.admin.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.UserDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IUserService;

@RestController
public class UserAPI {
	@Autowired
	private IUserService service;
	
    @GetMapping(value = "/api/roles/{id}/users")
    @ResponseBody
    public List<UserDTO> getAllByRole(@PathVariable Long id) {
        List<UserDTO> list = service.findAllByRoleId(id);
        return list;
    }
    
    @GetMapping(value = "/api/users/{id}")
    @ResponseBody
    public UserDTO getOneById(@PathVariable Long id) {
        return service.findOneById(id);
    }
    
    
    @PutMapping("/api/users/{id}")
	public MessageAlertModel update(@PathVariable Long id, @RequestBody UserDTO dto) {
    	dto.setId(id);
    	if(dto.getUserName() == null && dto.getRolesId() != null) return service.updateRoleUser(dto);
    	if(dto.getUserName() == null && dto.getStatus() != null) return service.updateStatusUser(dto);
    	if(dto.getOldPassword() != null) return service.updatePasswordUser(id, dto.getOldPassword(), dto.getPassword());
    	if(dto.getOldPassword() == null && dto.getPassword() != null && dto.getEmail() != null) return service.updatePasswordUser(dto.getEmail(), dto.getPassword());
		return service.update(dto);
	}
//    
//    @PutMapping("/api/roles/{id}")
//	public MessageAlertModel updateStatusType(@PathVariable Long id, @RequestBody RoleDTO dto) {
//    	dto.setId(id);
//		return service.update(dto);
//	}
//    
//    @DeleteMapping("/api/roles/{ids}")
//	public MessageAlertModel deleteStatusType(@PathVariable Long[] ids) {
//		return service.delete(ids);
//	}
}
