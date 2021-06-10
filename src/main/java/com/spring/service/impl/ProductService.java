package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.BrandDTO;
import com.spring.dto.ProductDTO;
import com.spring.dto.ReviewDTO;
import com.spring.entity.FilterEntity;
import com.spring.entity.ProductEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.FilterProductRepository;
import com.spring.repository.ProductRepository;
import com.spring.service.IBrandService;
import com.spring.service.IFilterProductService;
import com.spring.service.IProductService;
import com.spring.service.IReviewService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IFilterProductService filterService;
	
	@Autowired
	private FilterProductRepository filterRepository;
	
	@Autowired
	private IReviewService reviewService;

	@Autowired
	private ModelMapper modelMapper;

	/*
	 * ----------------GET--------------------
	 * 
	 */

	@Override
	public ProductDTO findOneByAlias(String alias) {
		ProductEntity entity = productRepository.findOneByAlias(alias);
		if (entity == null)
			return null;
		return modelMapper.map(entity, ProductDTO.class);
	}

	@Override
	public ProductDTO findOneById(Long id) {
		ProductEntity entity = productRepository.findOneById(id);
		if (entity == null)
			return null;

		ProductDTO p = modelMapper.map(entity, ProductDTO.class);
		Long[] filterIds = filterService.findIdAllByProduct(id);
		p.setFiltersId(filterIds);
		return p;
	}

	@Override
	public List<ProductDTO> findAllByBrand(long id) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByBrandId(id);

		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByBrand(String brandAlias) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		BrandDTO brandDTO = brandService.findOneByAlias(brandAlias);
		List<ProductEntity> entities = productRepository.findAllByBrandId(brandDTO.getId());

		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByCategoryAliasAndIsNewAndStatusAndLimit(String categoryAlias, boolean isNew, String statusCategory, String statusBrand, String statusProduct, int limit) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryAliasAndIsNewAndStatusAndLimit(categoryAlias, isNew, statusCategory, statusBrand, statusProduct, limit);

		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByIsAccessoryAndIsNewAndStatusAndLimit(boolean isAccessory, boolean isNew, String statusCategory, String statusBrand, String statusProduct,int limit) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryIsAccessoryAndIsNewAndStatusAndLimit(isAccessory,
				isNew, statusCategory, statusBrand, statusProduct, limit);

		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByCategoryAliasAndIsHotAndStatusAndLimit(String categoryAlias, boolean isHot, String statusCategory, String statusBrand, String statusProduct, int limit) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryAliasAndIsHotAndStatusAndLimit(categoryAlias, isHot, statusCategory, statusBrand, statusProduct, limit);

		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByIsAccessoryAndIsHotAndStatusAndLimit(boolean isAccessory, boolean isHot, String statusCategory, String statusBrand, String statusProduct, int limit) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryIsAccessoryAndIsHotAndStatusAndLimit(isAccessory,
				isHot, statusCategory, statusBrand, statusProduct, limit);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> findAllByCategoryAliasAndStatusAndLimitAndOffset(String categoryAlias,
			String statusCategory, String statusBrand, String productStatus, int limit, int offset) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryAliasAndStatusAndLimitAndOffset(categoryAlias, statusCategory, statusBrand, productStatus, limit, offset);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> findAllByCategoryIdAndStatusAndLimitAndOffset(Long id,
			String statusCategory, String statusBrand, String productStatus, int limit, int offset) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByCategoryIdAndStatusAndLimitAndOffset(id, statusCategory, statusBrand, productStatus, limit, offset);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> findAllByBrandIdAndStatusAndLimitAndOffset(Long id, String statusCategory,
			String statusBrand, String productStatus, int limit, int offset) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByBrandIdAndStatusAndLimitAndOffset(id, statusCategory, statusBrand, productStatus, limit, offset);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> searchByKeywordAndStatusAndLimitAndOffset(String keyword, String statusCategory,
			String statusBrand, String productStatus, int limit, int offset) {
		String keywordAlias = "%" + WebUtils.formatAlias(keyword) + "%";
		keyword = "%" + keyword + "%";
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.search(statusCategory, statusBrand, productStatus, keyword, keywordAlias, limit, offset);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> searchByTagsAndStatusAndLimitAndOffset(String keyword, String statusCategory,
			String statusBrand, String productStatus, int limit, int offset) {
		
		keyword = "%" + keyword + "%";
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.searchByTags(statusCategory, statusBrand, productStatus, keyword, limit, offset);
		
		for (ProductEntity item : entities) {
			ProductDTO dto = modelMapper.map(item, ProductDTO.class);
			result.add(dto);
		}
		return result;
	}

	/*
	 * ----------------SAVE, UDATE, DELETE--------------------
	 * 
	 */
	@Override
	public MessageAlertModel save(ProductDTO dto) {
		String alert = "", message = "";
		BrandDTO brandDTO = brandService.findOneById(dto.getBrandId());
		if (brandDTO != null) {
			dto.setAlias(brandDTO.getAlias() + "-" + WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Nhãn Hàng Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}

		ProductDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		dto.setStatus(StatusCustom.ACTIVE);
		ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
		List<FilterEntity> listFilter = new ArrayList<FilterEntity>();
		if(dto.getFiltersId() != null) {
			for(Long id : dto.getFiltersId()) {
				listFilter.add(filterRepository.findOneById(id));
			}
		}
		entity.setListFilter(listFilter);
		try {
			productRepository.save(entity);
			alert = "success";
			message = "Thêm Thành Công";
		} catch (Exception e) {
			alert = "danger";
			message = WebUtils.getMessageWhenErrorEntity(e.getMessage());
		}

		return new MessageAlertModel(alert, message, new Date());
	}

	@Override
	public MessageAlertModel update(ProductDTO dto) {
		String alert = "", message = "";
		BrandDTO brandDTO = brandService.findOneById(dto.getBrandId());
		if (brandDTO != null) {
			dto.setAlias(brandDTO.getAlias() + "-" + WebUtils.formatAlias(dto.getName()));
		} else {
			alert = "danger";
			message = "Nhãn Hàng Không Tồn Tại.";
			return new MessageAlertModel(alert, message, new Date());
		}

		ProductDTO oldEntity = findOneById(dto.getId());
		ProductDTO isExistDto = findOneByAlias(dto.getAlias());
		if (isExistDto != null && !oldEntity.getAlias().equals(isExistDto.getAlias())) {
			alert = "danger";
			message = "Tên này đã tồn tại.";
			return new MessageAlertModel(alert, message, new Date());
		}
		List<FilterEntity> listFilter = new ArrayList<FilterEntity>();
		
		dto.setCreatedBy(oldEntity.getCreatedBy());
		dto.setCreatedDate(oldEntity.getCreatedDate());
		ProductEntity entity = modelMapper.map(dto, ProductEntity.class);
		if(dto.getFiltersId() != null) {
			for(Long id : dto.getFiltersId()) {
				listFilter.add(filterRepository.findOneById(id));
			}
		}
		entity.setListFilter(listFilter);
		try {
			productRepository.saveAndFlush(entity);
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
		for (Long id : ids) {
			boolean isExist = productRepository.exists(id);
			if (isExist)
				productRepository.delete(id);
		}
		return new MessageAlertModel("success", "Đã xóa!", new Date());
	}

	@Override
	public int countByCategoryId(long categoryId) {
		return productRepository.countByCategoryId(categoryId);
	}

	@Override
	public int countByCategoryAliasAndStatus(String categoryAlias, String statusCategory,
			String statusBrand, String productStatus) {
		return productRepository.countByCategoryAliasAndStatus(categoryAlias, statusCategory, statusBrand, productStatus);
	}

	@Override
	public int countByBrandIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus) {
		return productRepository.countByBrandIdAndStatus(id, statusCategory, statusBrand, productStatus);
	}

	@Override
	public MessageAlertModel updateQtyProduct(Long idProduct, int qty) {
		ProductEntity productEntity = productRepository.findOneById(idProduct);
		if(productEntity != null ) {
			if(productEntity.getQty() < qty) return new MessageAlertModel("danger", "Số lượng sản phẩm không đủ", new Date());
			int newQty = productEntity.getQty() - qty;
			if(newQty == 0) productEntity.setStatus(StatusCustom.OUT_OF_STOCK);
			productEntity.setQty(newQty);
			productRepository.saveAndFlush(productEntity);
			return new MessageAlertModel("success", "Cập nhật số lượng sản phẩm thành công.", new Date());
		} else {
			return new MessageAlertModel("danger", "Sản phẩm không tồn tại", new Date());
		}
		
		
	}

	@Override
	public MessageAlertModel updateStarProduct(Long idProduct) {
		ProductEntity oldEntity = productRepository.getOne(idProduct);
		if(oldEntity == null) {
			return new MessageAlertModel("danger", "Sản phẩm không tồn tại!", new Date());
		}
		List<ReviewDTO> reviews = reviewService.findAllByProductId(idProduct);
		double star1 = 0, star2 = 0, star3 = 0, star4 = 0, star5 = 0;
		for(ReviewDTO review : reviews) {
			switch (review.getStar()) {
			case 1:
				star1++;
				break;
			case 2:
				star2++;
				break;
			case 3:
				star3++;
				break;
			case 4:
				star4++;
				break;
			case 5:
				star5++;
				break;
			default:
				break;
			}
		}
		
		double avg = (star5 * 5 + star4 * 4 + star3 * 3 + star2 * 2 + star1 * 1) / (reviews.size());
		int avgStar = (int) Math.round(avg);
		oldEntity.setStar(avgStar);
		try {
			productRepository.saveAndFlush(oldEntity);
			return new MessageAlertModel("success", "Cập nhật sao đánh giá thành công", new Date());
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
	}

	@Override
	public List<ProductDTO> findAllByCategoryIdAndFilterAndStatusAndLimitAndOffset(Long id, String filterId,
			String statusCategory, String statusBrand, String productStatus, int limit, int offset) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entitiesFull = productRepository.findAllByCategoryIdAndStatus(id, statusCategory, statusBrand, productStatus);
		List<ProductEntity> entitiesCondition = new ArrayList<ProductEntity>();
		
		Long [] idsFilter = new Long[filterId.split(",").length];
		
		int i = 0;
		for(String s : filterId.split(",")) {
			idsFilter[i] = Long.parseLong(s);
			i++;
		}
if(idsFilter[0] == null) {
			
			int index = 0;
			int oset = offset;
			while(true) {
				if(index == limit || oset >= entitiesFull.size()) break;
				ProductDTO dto = modelMapper.map(entitiesFull.get(oset), ProductDTO.class);
				result.add(dto);
				oset++;
				index++;
			}
			return result;
		}
		for (ProductEntity item : entitiesFull) {
			int match = 0;
			for(int j = 0; j < item.getListFilter().size(); j++) {
				for(int k = 0; k < idsFilter.length; k ++) {
					if(idsFilter[k] == item.getListFilter().get(j).getId()) {
						match++;
					}
				}
			}
			if(match == idsFilter.length) {
				entitiesCondition.add(item);
			}
			
		}
		int index = 0;
		int oset = offset;
		while(true) {
			if(index == limit || oset >= entitiesCondition.size()) break;
			ProductDTO dto = modelMapper.map(entitiesCondition.get(oset), ProductDTO.class);
			result.add(dto);
			oset++;
			index++;
		}
		return result;
	}

	@Override
	public List<ProductDTO> findAllByBrandIdAndFilterAndStatusAndLimitAndOffset(Long id, String filterId,
			String statusCategory, String statusBrand, String productStatus, int limit, int offset) {
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		List<ProductEntity> entitiesFull = productRepository.findAllByBrandIdAndStatus(id, statusCategory, statusBrand, productStatus);
		List<ProductEntity> entitiesCondition = new ArrayList<ProductEntity>();
		Long [] idsFilter = new Long[filterId.split(",").length];
		
		int i = 0;
		for(String s : filterId.split(",")) {
			idsFilter[i] = Long.parseLong(s);
			i++;
		}
		if(idsFilter[0] == null) {

			int index = 0;
			int oset = offset;
			while(true) {
				if(index == limit || oset >= entitiesFull.size()) break;
				ProductDTO dto = modelMapper.map(entitiesFull.get(oset), ProductDTO.class);
				result.add(dto);
				oset++;
				index++;
			}
			return result;
		}
		for (ProductEntity item : entitiesFull) {
			int match = 0;
			for(int j = 0; j < item.getListFilter().size(); j++) {
				for(int k = 0; k < idsFilter.length; k ++) {
					if(idsFilter[k] == item.getListFilter().get(j).getId()) {
						match++;
					}
				}
			}
			if(match == idsFilter.length) {
				entitiesCondition.add(item);
			}
			
		}
		int index = 0;
		int oset = offset;
		while(true) {
			if(index == limit || oset >= entitiesCondition.size()) break;
			ProductDTO dto = modelMapper.map(entitiesCondition.get(oset), ProductDTO.class);
			result.add(dto);
			oset++;
			index++;
		}
		return result;
	}

	@Override
	public int countByCategoryIdAndFilterAndStatus(Long id, String filter, String statusCategory, String statusBrand,
			String productStatus) {
		int result = 0;
		List<ProductEntity> entities = productRepository.findAllByCategoryIdAndStatus(id, statusCategory, statusBrand, productStatus);
		Long [] idsFilter = new Long[filter.split(",").length];
		
		int i = 0;
		for(String s : filter.split(",")) {
			idsFilter[i] = Long.parseLong(s);
			i++;
		}
		if(idsFilter[0] == null) return entities.size();
		for (ProductEntity item : entities) {
			int match = 0;
			for(int j = 0; j < item.getListFilter().size(); j++) {
				for(int k = 0; k < idsFilter.length; k ++) {
					if(idsFilter[k] == item.getListFilter().get(j).getId()) {
						match++;
					}
				}
			}
			if(match == idsFilter.length) result++;
			
		}
		return result;
	}
	
	@Override
	public int countByBrandIdAndFilterAndStatus(Long id, String filter, String statusCategory, String statusBrand,
			String productStatus) {
		int result = 0;
		List<ProductEntity> entities = productRepository.findAllByBrandIdAndStatus(id, statusCategory, statusBrand, productStatus);
		Long [] idsFilter = new Long[filter.split(",").length];
		if(idsFilter[0] == null) return entities.size();
		int i = 0;
		for(String s : filter.split(",")) {
			idsFilter[i] = Long.parseLong(s);
			i++;
		}
		if(idsFilter[0] == null) return entities.size();
		for (ProductEntity item : entities) {
			int match = 0;
			for(int j = 0; j < item.getListFilter().size(); j++) {
				for(int k = 0; k < idsFilter.length; k ++) {
					if(idsFilter[k] == item.getListFilter().get(j).getId()) {
						match++;
					}
				}
			}
			if(match == idsFilter.length) result++;
			
		}
		return result;
	}

	@Override
	public int countSearch(String keyword, String statusCategory, String statusBrand, String productStatus) {
		String keywordAlias = "%" + WebUtils.formatAlias(keyword) + "%";
		keyword = "%" + keyword + "%";
		return productRepository.countSearch(statusCategory, statusBrand, productStatus, keyword, keywordAlias);
	}

	@Override
	public int countSearchByTags(String keyword, String statusCategory, String statusBrand, String productStatus) {
		keyword = "%" + keyword + "%";
		return productRepository.countSearchByTags(statusCategory, statusBrand, productStatus, keyword);
	}



	
}
