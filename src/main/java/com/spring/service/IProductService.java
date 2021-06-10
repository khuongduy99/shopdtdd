package com.spring.service;

import java.util.List;

import com.spring.dto.ProductDTO;
import com.spring.model.MessageAlertModel;

public interface IProductService {
	ProductDTO findOneByAlias(String alias);
	ProductDTO findOneById(Long id);
	
	List<ProductDTO> findAllByBrand(long id);
	List<ProductDTO> findAllByBrand(String brandAlias);
	
	List<ProductDTO> findAllByCategoryAliasAndIsNewAndStatusAndLimit(String categoryAlias, boolean isNew, String statusCategory, String brandStatus, String statusProduct, int limit);
	List<ProductDTO> findAllByIsAccessoryAndIsNewAndStatusAndLimit(boolean isAccessory, boolean isNew, String statusCategory, String brandStatus, String statusProduct, int limit);
	
	List<ProductDTO> findAllByCategoryAliasAndIsHotAndStatusAndLimit(String categoryAlias, boolean isHot, String statusCategory, String brandStatus, String statusProduct, int limit);
	List<ProductDTO> findAllByIsAccessoryAndIsHotAndStatusAndLimit(boolean isAccessory, boolean isHot, String statusCategory, String brandStatus, String statusProduct, int limit);
	
	List<ProductDTO> findAllByCategoryAliasAndStatusAndLimitAndOffset(String categoryAlias, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	List<ProductDTO> findAllByCategoryIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	List<ProductDTO> findAllByBrandIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	List<ProductDTO> findAllByCategoryIdAndFilterAndStatusAndLimitAndOffset(Long id, String filterId, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	List<ProductDTO> findAllByBrandIdAndFilterAndStatusAndLimitAndOffset(Long id, String filterId, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	MessageAlertModel save(ProductDTO dto);
	MessageAlertModel update(ProductDTO dto);
	MessageAlertModel updateQtyProduct(Long idProduct, int qty);
	MessageAlertModel updateStarProduct(Long idProduct);
	MessageAlertModel delete(Long[]ids);
	
	int countByCategoryId(long categoryId);
	int countByCategoryIdAndFilterAndStatus(Long id, String filter, String statusCategory, String statusBrand, String productStatus);
	int countByBrandIdAndFilterAndStatus(Long id, String filter, String statusCategory, String statusBrand, String productStatus);
	int countByCategoryAliasAndStatus(String categoryAlias, String statusCategory, String statusBrand, String productStatus);
	int countByBrandIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus);
	int countSearch(String keyword, String statusCategory, String statusBrand, String productStatus);
	int countSearchByTags(String keyword, String statusCategory, String statusBrand, String productStatus);
	
	List<ProductDTO> searchByKeywordAndStatusAndLimitAndOffset(String keyword, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	List<ProductDTO> searchByTagsAndStatusAndLimitAndOffset(String keyword, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
}
