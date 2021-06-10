package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByAlias(String alias);
	List<CategoryEntity> findAll();
	List<CategoryEntity> findAllByIsAccessoryAndStatus(boolean isAccessory, String status);
	List<CategoryEntity> findAllByIsAccessory(boolean isAccessory);
	int countByIsAccessory(boolean isAccessory);
}
