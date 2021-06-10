package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>{
	List<BrandEntity> findAllByCategoryId(long categoryId);
	
	BrandEntity findOneById(long id);
	BrandEntity findOneByAlias(String alias);
	
	@Query(value = "SELECT b.* FROM brand b JOIN category c ON b.category_id = c.id WHERE c.alias = ?1 AND c.status = ?2 AND b.status = ?3", nativeQuery = true)
	List<BrandEntity> findAllByCategoryAliasAndStatus(String categoryAlias, String statusCategory, String statusBrand);
}
