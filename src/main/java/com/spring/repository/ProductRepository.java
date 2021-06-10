package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	List<ProductEntity> findAllByBrandId(Long id);
	
	
	
	ProductEntity findOneById(Long id);
	
	ProductEntity findOneByAlias(String alias);
	
//	@Query("SELECT COUNT(p.id) FROM com.spring.entity.ProductEntity p JOIN com.spring.entity.BrandEntity b on p.brand_id = b.id JOIN com.spring.entity.CategoryEntity c ON c.id = b.category_id WHERE c.id = ?1")
//    int countByCategoryId(long categoryId);
	
//	@Query(value = "SELECT COUNT(p.id) FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.id = ?1", nativeQuery = true)
//    int countByCategoryId(Long id);
//	
//	@Query(value = "SELECT COUNT(p.id) FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.alias = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
//    int countByCategoryAliasAndStatus(String categoryAlias, String statusCategory, String statusBrand, String productStatus);
//	
//	@Query(value = "SELECT COUNT(p.id) FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE b.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
//    int countByBrandIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.alias = ?1 AND p.is_new = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
//    List<ProductEntity> findAllByCategoryAliasAndIsNewAndStatusAndLimit(String categoryAlias, boolean isNew, String statusCategory, String statusBrand, String productStatus, int limit);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.is_accessory = ?1 AND p.is_new = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
//	List<ProductEntity> findAllByCategoryIsAccessoryAndIsNewAndStatusAndLimit(boolean isAccessory, boolean isNew, String statusCategory, String statusBrand, String productStatus, int limit);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.alias = ?1 AND p.is_hot = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
//    List<ProductEntity> findAllByCategoryAliasAndIsHotAndStatusAndLimit(String categoryAlias, boolean isHot, String statusCategory, String statusBrand, String productStatus, int limit);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.is_accessory = ?1 AND p.is_hot = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
//	List<ProductEntity> findAllByCategoryIsAccessoryAndIsHotAndStatusAndLimit(boolean isAccessory, boolean isHot, String statusCategory, String statusBrand, String productStatus, int limit);
//	
//	
//	//store
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.alias = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
//	List<ProductEntity> findAllByCategoryAliasAndStatusAndLimitAndOffset(String categoryAlias, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE c.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
//	List<ProductEntity> findAllByCategoryIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
//	
//	@Query(value = "SELECT p.* FROM PRODUCT p JOIN BRAND b ON p.brand_id = b.id JOIN CATEGORY c ON c.id = b.category_id WHERE b.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
//	List<ProductEntity> findAllByBrandIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Query(value = "SELECT COUNT(p.id) FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.id = ?1", nativeQuery = true)
    int countByCategoryId(Long id);
	
	@Query(value = "SELECT COUNT(p.id) FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.alias = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
    int countByCategoryAliasAndStatus(String categoryAlias, String statusCategory, String statusBrand, String productStatus);
	
	@Query(value = "SELECT COUNT(p.id) FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE b.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
    int countByBrandIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus);
	
	@Query(value = "SELECT COUNT(p.id) FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id "
			+ "WHERE c.status = ?1 AND b.status = ?2 AND p.status = ?3 AND p.name LIKE ?4 OR p.alias LIKE ?5 OR p.tags LIKE ?6", nativeQuery = true)
	int countSearch(String statusCategory, String statusBrand, String productStatus, String keyword, String keywordAlias, String tags);
	
	@Query(value = "SELECT COUNT(p.id) FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id "
			+ "WHERE c.status = ?1 AND b.status = ?2 AND p.status = ?3 AND p.tags LIKE ?4", nativeQuery = true)
	int countSearchByTags(String statusCategory, String statusBrand, String productStatus, String keyword);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.alias = ?1 AND p.is_new = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
    List<ProductEntity> findAllByCategoryAliasAndIsNewAndStatusAndLimit(String categoryAlias, boolean isNew, String statusCategory, String statusBrand, String productStatus, int limit);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.is_accessory = ?1 AND p.is_new = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
	List<ProductEntity> findAllByCategoryIsAccessoryAndIsNewAndStatusAndLimit(boolean isAccessory, boolean isNew, String statusCategory, String statusBrand, String productStatus, int limit);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.alias = ?1 AND p.is_hot = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
    List<ProductEntity> findAllByCategoryAliasAndIsHotAndStatusAndLimit(String categoryAlias, boolean isHot, String statusCategory, String statusBrand, String productStatus, int limit);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.is_accessory = ?1 AND p.is_hot = ?2 AND c.status = ?3 AND b.status = ?4 AND  p.status = ?5 LIMIT ?6", nativeQuery = true)
	List<ProductEntity> findAllByCategoryIsAccessoryAndIsHotAndStatusAndLimit(boolean isAccessory, boolean isHot, String statusCategory, String statusBrand, String productStatus, int limit);
	
	
	//store
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.alias = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
	List<ProductEntity> findAllByCategoryAliasAndStatusAndLimitAndOffset(String categoryAlias, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
	List<ProductEntity> findAllByCategoryIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE b.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
	List<ProductEntity> findAllByBrandIdAndStatusAndLimitAndOffset(Long id, String statusCategory, String statusBrand, String productStatus, int limit, int offset);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE c.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
	List<ProductEntity> findAllByCategoryIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id WHERE b.id = ?1 AND c.status = ?2 AND b.status = ?3 AND p.status = ?4", nativeQuery = true)
	List<ProductEntity> findAllByBrandIdAndStatus(Long id, String statusCategory, String statusBrand, String productStatus);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id "
			+ "WHERE c.status = ?1 AND b.status = ?2 AND p.status = ?3 AND p.name LIKE ?4 OR p.alias LIKE ?5 OR p.tags LIKE ?6 LIMIT ?7 OFFSET ?8", nativeQuery = true)
	List<ProductEntity> search(String statusCategory, String statusBrand, String productStatus, String keyword, String keywordAlias, String tags, int limit, int offset);
	
	@Query(value = "SELECT p.* FROM product p JOIN brand b ON p.brand_id = b.id JOIN category c ON c.id = b.category_id "
			+ "WHERE c.status = ?1 AND b.status = ?2 AND p.status = ?3 AND p.tags LIKE ?4 LIMIT ?5 OFFSET ?6", nativeQuery = true)
	List<ProductEntity> searchByTags(String statusCategory, String statusBrand, String productStatus, String keyword, int limit, int offset);
	
	
}
 