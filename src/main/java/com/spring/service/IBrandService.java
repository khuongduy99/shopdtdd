package com.spring.service;

import java.util.List;

import com.spring.dto.BrandDTO;
import com.spring.model.MessageAlertModel;

public interface IBrandService {
	BrandDTO findOneByAlias(String alias);
	BrandDTO findOneById(Long id);
	List<BrandDTO> findAllByCategory(long id);
	List<BrandDTO> findAllByCategory(String categoryAlias);
	MessageAlertModel save(BrandDTO dto);
	MessageAlertModel update(BrandDTO dto);
	MessageAlertModel delete(Long[]ids);
	
	List<BrandDTO> findAllByCategoryAliasAndStatus(String categoryAlias, String statusCategory, String statusBrand);
}
