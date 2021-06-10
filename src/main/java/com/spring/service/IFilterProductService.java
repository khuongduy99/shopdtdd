package com.spring.service;

import java.util.List;

import com.spring.dto.FilterDTO;
import com.spring.model.MessageAlertModel;

public interface IFilterProductService {
	FilterDTO findOneByAlias(String alias);
	FilterDTO findOneById(Long id);
	List<FilterDTO> findAllByCategory(long id);
	List<FilterDTO> findAllByCategory(String categoryAlias);
	Long[] findIdAllByProduct(Long id);
	MessageAlertModel save(FilterDTO dto);
	boolean save(Long productId, Long filterId);
	MessageAlertModel update(List<FilterDTO> dtos);
	MessageAlertModel update(FilterDTO dto);
	MessageAlertModel delete(Long[]ids);
}
