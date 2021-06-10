package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Long>{
//	List<BrandEntity> findAllByCategoryId(long categoryId);
//	
//	BrandEntity findOneById(long id);
//	BrandEntity findOneByAlias(String alias);
//	
	@Query(value = "SELECT SUM(total_money) FROM bill WHERE YEAR(updated_date) = ?1 AND MONTH(updated_date) = ?2 AND status ='Đã giao hàng'", nativeQuery = true)
	int sumMoneyByYearAndMonth(int year, int month);
	
	@Query(value = "SELECT SUM(total_money) FROM bill WHERE YEAR(updated_date) = ?1 AND MONTH(updated_date) = ?2 AND DAY(updated_date) = ?3 AND status ='Đã giao hàng'", nativeQuery = true)
	int sumMoneyByYearAndMonthAndDay(int year, int month, int day);
	
	@Query(value = "SELECT COUNT(id) FROM bill WHERE YEAR(updated_date) = ?1 AND MONTH(updated_date) = ?2 AND DAY(updated_date) = ?3 AND status = ?4", nativeQuery = true)
	int countByYearAndMonthAndDayAndStatus(int year, int month, int day, String status);
	
	@Query(value = "SELECT COUNT(id) FROM bill WHERE YEAR(updated_date) = ?1 AND MONTH(updated_date) = ?2 AND status = ?3", nativeQuery = true)
	int countByYearAndMonthAndStatus(int year, int month, String status);
	
	@Query(value = "SELECT COUNT(id) FROM bill WHERE YEAR(updated_date) = ?1 AND status = ?2", nativeQuery = true)
	int countByYearAndStatus(int year, String status);
	
	List<BillEntity> findAllByUserIdAndStatus(Long userId, String status);
	List<BillEntity> findAllByUserId(Long userId);
	List<BillEntity> findAllByStatus(String status);
	
	BillEntity findOneByCode(String code);
}
