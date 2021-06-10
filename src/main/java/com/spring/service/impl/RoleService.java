package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.RoleDTO;
import com.spring.entity.RoleEntity;
import com.spring.entity.UserEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.RoleRepository;
import com.spring.service.IRoleService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class RoleService implements IRoleService{
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * ----------------GET--------------------
	 * 
	 */
	
	@Override
	public RoleDTO findOneById(Long id) {
		RoleEntity entity = roleRepository.findOneById(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, RoleDTO.class);
	}

	@Override
	public List<RoleDTO> findAll() {
		List<RoleDTO> result = new ArrayList<RoleDTO>();
		List<RoleEntity> entities = roleRepository.findAll();

		for (RoleEntity item : entities) {
			RoleDTO dto = modelMapper.map(item, RoleDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public RoleDTO findOneByAlias(String alias) {
		RoleEntity entity = roleRepository.findOneByAlias(alias);
		if (entity == null)
			return null;
		return modelMapper.map(entity, RoleDTO.class);
	}
	
	/*
	 * ----------------SAVE, UDATE, DELETE--------------------
	 * 
	 */

	@Override
	public MessageAlertModel save(RoleDTO dto) {
		String alert = "", message = "";

		dto.setAlias(WebUtils.formatAlias(dto.getName()));
		RoleDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setStatus(StatusCustom.ACTIVE);
		RoleEntity entity = modelMapper.map(dto, RoleEntity.class);
		try {
			roleRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(RoleDTO dto) {
		String alert = "", message = "";
		dto.setAlias(WebUtils.formatAlias(dto.getName()));
		RoleDTO oldEntity = findOneById(dto.getId());
		RoleDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null && !oldEntity.getAlias().equals(isExistDto.getAlias())) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		
		RoleEntity entity = modelMapper.map(dto, RoleEntity.class);
		try {
			roleRepository.saveAndFlush(entity);
			alert = "success";
			message = "Cập Nhật Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel delete(Long[] ids) {
		for(Long id : ids) {
			RoleEntity role = roleRepository.findOneById(id);
			if(!role.getAlias().equals("admin") && !role.getAlias().equals("user")) {
				role.setListUser(new ArrayList<UserEntity>());
				roleRepository.saveAndFlush(role);
				roleRepository.delete(role);
			}
			
		}
		return new MessageAlertModel("success", "Đã xóa!", new Date());
	}

}
