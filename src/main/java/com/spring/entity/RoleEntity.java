package com.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
	
	@Column
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"),  inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> listUser = new ArrayList<>();
	 
	
	public List<UserEntity> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserEntity> listUser) {
		this.listUser = listUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
