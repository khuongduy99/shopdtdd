package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
	@Query(value = "SELECT r.id, r.create_by, r.create_date, r.status, r.updated_by, r.updated_date, r.comment, r.star, r.product_id, r.user_id, r.alias, u.full_name AS "
			+ "name FROM review r JOIN user u ON r.user_id = u.id WHERE r.product_id = ?1 ORDER BY r.create_date DESC LIMIT ?2 OFFSET ?3 ", nativeQuery = true)
	List<ReviewEntity> findAllByProductIdAndLimitAndOffset(Long id, int limit, int offset);
	
	@Query(value = "SELECT r.id, r.create_by, r.create_date, r.status, r.updated_by, r.updated_date, r.comment, r.star, r.product_id, r.user_id, r.alias, u.full_name AS "
			+ "name FROM review r JOIN user u ON r.user_id = u.id WHERE r.product_id = ?1", nativeQuery = true)
	List<ReviewEntity> findAllByProductId(Long id);
	
	ReviewEntity findOneByProductIdAndUserId(Long productId, Long userId);
	
	@Query(value = "SELECT  r.id, r.create_by, r.create_date, r.status, r.updated_by, r.updated_date, r.comment, r.star, r.product_id, r.user_id, r.alias, u.full_name AS name FROM review r JOIN user u ON r.user_id = u.id WHERE r.id = ?1", nativeQuery = true)
	ReviewEntity findOneById(Long id);
}
