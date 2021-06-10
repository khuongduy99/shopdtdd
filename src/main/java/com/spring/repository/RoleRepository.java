package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneByName(String name);
	RoleEntity findOneById(Long id);
	RoleEntity findOneByAlias(String alias);
	List<RoleEntity> findAll();
}
