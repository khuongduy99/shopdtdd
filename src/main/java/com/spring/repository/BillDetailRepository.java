package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.BillDetailEntity;

public interface BillDetailRepository extends JpaRepository<BillDetailEntity, Long>{
	List<BillDetailEntity> findAllByBillId(Long id);
	
}
