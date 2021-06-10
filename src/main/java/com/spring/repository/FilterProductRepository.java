package com.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.FilterEntity;

public interface FilterProductRepository extends JpaRepository<FilterEntity, Long>{
	List<FilterEntity> findAllByCategoryId(Long categoryId);
	
//	@Query(value = "SELECT fp.filter_id  FROM PRODUCT p JOIN FILTER_PRODUCT fp ON p.id = fp.product_id WHERE p.id = ?1", nativeQuery = true)
//	Long[] findIdAllByProductId(Long productId);
//	
	
	@Query(value = "SELECT fp.filter_id  FROM product p JOIN filter_product fp ON p.id = fp.product_id WHERE p.id = ?1", nativeQuery = true)
	Long[] findIdAllByProductId(Long productId);
	
	 @Modifying
	    @Query(value = "insert into filter_product (product_id,filter_id) VALUES (:productId,:filterId)", nativeQuery = true)
	    @Transactional
	    void save(@Param("productId") Long productId, @Param("productId") Long filterId);
	
	
	FilterEntity findOneById(long id);
	FilterEntity findOneByAlias(String alias);
}
