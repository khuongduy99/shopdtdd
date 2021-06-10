package com.spring.service;

import java.util.List;

import com.spring.dto.UserDTO;
import com.spring.model.MessageAlertModel;

public interface IUserService {
	 List<UserDTO> findAllByRoleAlias(String roleAlias);
	 List<UserDTO> findAllByRoleId(Long roleId);
	 UserDTO findOneById(Long id);
	 
	 MessageAlertModel register(UserDTO dto);
	 MessageAlertModel login(String userName, String password);
	 MessageAlertModel save(UserDTO dto);
	 MessageAlertModel update(UserDTO dto);
	 MessageAlertModel forgotPassword(String email);
	 
	 MessageAlertModel updateRoleUser(UserDTO dto);
	 MessageAlertModel updateStatusUser(UserDTO dto);
	 MessageAlertModel updatePasswordUser(Long id, String oldPass, String newPass);
	 MessageAlertModel updatePasswordUser(String email, String password);
	 
	 int countByYearAndMonth(int year, int month);
	 int countByYearAndMonthAndDay(int year, int month, int day);
	 int countByRoleId(Long id);
}
