package com.spring.service;

import java.util.List;

import com.spring.dto.RoleDTO;
import com.spring.model.MessageAlertModel;

public interface IRoleService {
	RoleDTO findOneById(Long id);
	RoleDTO findOneByAlias(String alias);
	List<RoleDTO> findAll();
	
	MessageAlertModel save(RoleDTO dto);
	MessageAlertModel update(RoleDTO dto);
	MessageAlertModel delete(Long[]ids);
}
