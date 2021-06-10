package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.CategoryDTO;
import com.spring.entity.CategoryEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.CategoryRepository;
import com.spring.service.ICategoryService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * ----------------GET--------------------
	 * 
	 */

	@Override
	public CategoryDTO findOneByAlias(String alias) {
		CategoryEntity entity = categoryRepository.findOneByAlias(alias);
		if (entity == null)
			return null;
		return modelMapper.map(entity, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> findAllByIsAccessoryAndStatus(boolean isAccessory, String status) {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<CategoryEntity> entities = categoryRepository.findAllByIsAccessoryAndStatus(isAccessory, status);

		for (CategoryEntity item : entities) {
			CategoryDTO dto = modelMapper.map(item, CategoryDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<CategoryDTO> findAllByIsAccessory(boolean isAccessory) {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<CategoryEntity> entities = categoryRepository.findAllByIsAccessory(isAccessory);

		for (CategoryEntity item : entities) {
			CategoryDTO dto = modelMapper.map(item, CategoryDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public CategoryDTO findOneById(Long id) {
		CategoryEntity entity = categoryRepository.findOne(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, CategoryDTO.class);
	}
	
	@Override
	public int countByIsAccessory(boolean isAccessory) {
		return categoryRepository.countByIsAccessory(isAccessory);
	}

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		List<CategoryEntity> entities = categoryRepository.findAll();

		for (CategoryEntity item : entities) {
			CategoryDTO dto = modelMapper.map(item, CategoryDTO.class);
			result.add(dto);
		}
		return result;
	}

	/*
	 * ----------------SAVE, UDATE, DELETE--------------------
	 * 
	 */
	@Override
	public MessageAlertModel save(CategoryDTO dto) {
		String alert = "", message = "";

		dto.setAlias(WebUtils.formatAlias(dto.getName()));
		CategoryDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setStatus(StatusCustom.ACTIVE);
		CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
		try {
			categoryRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(CategoryDTO dto) {
		String alert = "", message = "";
		dto.setAlias(WebUtils.formatAlias(dto.getName()));
		CategoryDTO oldEntity = findOneById(dto.getId());
		CategoryDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null && !oldEntity.getAlias().equals(isExistDto.getAlias())) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setCreatedBy(oldEntity.getCreatedBy());
		dto.setCreatedDate(oldEntity.getCreatedDate());
		CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
		try {
			categoryRepository.saveAndFlush(entity);
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
			boolean isExist = categoryRepository.exists(id);
			if(isExist) categoryRepository.delete(id);
		}
		return new MessageAlertModel("success", "Đã xóa!", new Date());
	}

}
