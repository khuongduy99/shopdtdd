package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.CategoryDTO;
import com.spring.dto.FilterDTO;
import com.spring.entity.FilterEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.FilterProductRepository;
import com.spring.service.ICategoryService;
import com.spring.service.IFilterProductService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class FilterService implements IFilterProductService{
	
	@Autowired
	private FilterProductRepository filterRepository;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ModelMapper modelMapper;

	/*
	 * ----------------GET--------------------
	 * 
	 */
	
	@Override
	public FilterDTO findOneByAlias(String alias) {
		FilterEntity entity = filterRepository.findOneByAlias(alias);
		if (entity == null)
			return null;
		return modelMapper.map(entity, FilterDTO.class);
	}
	
	@Override
	public FilterDTO findOneById(Long id) {
		FilterEntity entity = filterRepository.findOneById(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, FilterDTO.class);
	}
	
	@Override
	public List<FilterDTO> findAllByCategory(long id) {
		List<FilterDTO> result = new ArrayList<FilterDTO>();
		List<FilterEntity> entities = filterRepository.findAllByCategoryId(id);

		for (FilterEntity item :  entities) {
			FilterDTO dto = modelMapper.map(item, FilterDTO.class);
			result.add(dto);
		}
		
		 List<FilterDTO> res = new ArrayList<FilterDTO>();
	        for(FilterDTO filter : result) {
	        	if(filter.getParentId() == 0L) {
	        		res.add(filter);
	        	}
	        }
	        
	        for(FilterDTO filter : result) {
	        	if(filter.getParentId() != 0L) {
	        		 for(int i = 0; i < res.size(); i++) {
	        	        	if(filter.getParentId() == res.get(i).getId()) {
	        	        		res.get(i).addChildren(filter);
	        	        	}
	        	        }
	        	}
	        }
		return res;
	}

	@Override
	public List<FilterDTO> findAllByCategory(String categoryAlias) {
		List<FilterDTO> result = new ArrayList<FilterDTO>();
		CategoryDTO categoryDTO = categoryService.findOneByAlias(categoryAlias);
		List<FilterEntity> entities = filterRepository.findAllByCategoryId(categoryDTO.getId());

		for (FilterEntity item :  entities) {
			FilterDTO dto = modelMapper.map(item, FilterDTO.class);
			result.add(dto);
		}
		
		 List<FilterDTO> res = new ArrayList<FilterDTO>();
	        for(FilterDTO filter : result) {
	        	if(filter.getParentId() == 0L) {
	        		res.add(filter);
	        	}
	        }
	        
	        for(FilterDTO filter : result) {
	        	if(filter.getParentId() != 0L) {
	        		 for(int i = 0; i < res.size(); i++) {
	        	        	if(filter.getParentId() == res.get(i).getId()) {
	        	        		res.get(i).addChildren(filter);
	        	        	}
	        	        }
	        	}
	        }
		return res;
	}
	

	@Override
	public Long[] findIdAllByProduct(Long id) {
		return filterRepository.findIdAllByProductId(id);
	}
	
	
	/*
	 * ----------------SAVE, UDATE, DELETE--------------------
	 * 
	 */

	@Override
	public MessageAlertModel save(FilterDTO dto) {
		String alert = "", message = "";
		CategoryDTO categoryDTO = categoryService.findOneById(dto.getCategoryId());
		if(categoryDTO != null) {
			dto.setAlias(categoryDTO.getAlias() +"-"+ WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Thể Loại Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		
		FilterDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setParentId(0L);
		dto.setStatus(StatusCustom.ACTIVE);
		FilterEntity entity = modelMapper.map(dto, FilterEntity.class);
		try {
			filterRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	

	@Override
	public MessageAlertModel delete(Long[] ids) {
		for(Long id : ids) {
			try {
				boolean isExist = filterRepository.exists(id);
				if(isExist) filterRepository.delete(id);
			} catch (Exception e) {
				String alert = "danger";
				String message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
				return new MessageAlertModel(alert, message, new Date());
			}
			
		}
		return new MessageAlertModel("success", "Đã xóa!", new Date());
	}

	@Override
	public MessageAlertModel update(List<FilterDTO> dtos) {
		String alert = "", message = "";
		try {
			for(FilterDTO dto : dtos) {
				
				FilterEntity entity = filterRepository.findOneById(dto.getId());
				entity.setParentId(0L);
				filterRepository.saveAndFlush(entity);
			}
			
			for(FilterDTO dto : dtos) {
				if(dto.getChildren() != null) {
					for(FilterDTO subDTO : dto.getChildren()) {
						FilterEntity entity = filterRepository.findOneById(subDTO.getId());
						entity.setParentId(dto.getId());
						filterRepository.saveAndFlush(entity);
					}
				}
			}
			alert = "success";
			message = "Cập nhật thành công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(FilterDTO dto) {
		String alert = "", message = "";
		CategoryDTO categoryDTO = categoryService.findOneById(dto.getCategoryId());
		if(categoryDTO != null) {
			dto.setAlias(categoryDTO.getAlias()  +"-"+ WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Thể Loại Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		FilterEntity oldEntity = filterRepository.findOneById(dto.getId());
		FilterDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null && !oldEntity.getAlias().equals(isExistDto.getAlias())) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		oldEntity.setName(dto.getName());
		oldEntity.setAlias(dto.getAlias());
		try {
			filterRepository.saveAndFlush(oldEntity);
			alert = "success";
			message = "Cập Nhật Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}
		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	@Transactional
	public boolean save(Long productId, Long filterId) {
		try {
			filterRepository.save(productId, filterId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


}
