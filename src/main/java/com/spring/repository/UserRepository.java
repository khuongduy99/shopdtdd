package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	@Query(value = "SELECT u.* FROM user u JOIN user_role ur ON u.id = ur.user_id WHERE ur.role_id = ?1", nativeQuery = true)
	List<UserEntity> findAllByRoleId(Long id);
	
	@Query(value = "SELECT COUNT(id) FROM user WHERE YEAR(create_date) = ?1 AND MONTH(create_date) = ?2", nativeQuery = true)
	int countByYearAndMonth(int year, int month);
	
	@Query(value = "SELECT COUNT(id) FROM user WHERE YEAR(create_date) = ?1 AND MONTH(create_date) = ?2 AND DAY(create_date) = ?3", nativeQuery = true)
	int countByYearAndMonthAndDay(int year, int month, int day);
	
	@Query(value = "SELECT COUNT(id) FROM user JOIN user_role ON user.id = user_role.user_id WHERE user_role.role_id = ?1", nativeQuery = true)
	int countByRoleId(Long roleId);
	
	UserEntity findOneByEmail(String email);
	UserEntity findOneByUserName(String userName);
	UserEntity findOneByEmailAndPassword(String email,String password);
	UserEntity findOneByFullName(String name);
	UserEntity findOneById(Long id);
}
