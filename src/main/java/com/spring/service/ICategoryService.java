package com.spring.service;

import java.util.List;

import com.spring.dto.CategoryDTO;
import com.spring.model.MessageAlertModel;

public interface ICategoryService {
	CategoryDTO findOneById(Long id);
	CategoryDTO findOneByAlias(String alias);
	List<CategoryDTO> findAllByIsAccessoryAndStatus(boolean isAccessory, String status);
	List<CategoryDTO> findAllByIsAccessory(boolean isAccessory);
	List<CategoryDTO> findAll();
	MessageAlertModel save(CategoryDTO dto);
	MessageAlertModel update(CategoryDTO dto);
	MessageAlertModel delete(Long[]ids);
	int countByIsAccessory(boolean isAccessory);
}
