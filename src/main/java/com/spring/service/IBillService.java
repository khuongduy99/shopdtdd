package com.spring.service;

import java.util.List;

import com.spring.dto.BillDTO;
import com.spring.model.MessageAlertModel;

public interface IBillService {
	MessageAlertModel order(BillDTO dto);
	MessageAlertModel save(BillDTO dto);
	MessageAlertModel update(BillDTO dto);
	MessageAlertModel delete(Long[]ids);
	
	BillDTO findOneById(Long id);
	List<BillDTO> findAllByUserIdAndStatus(Long userId, String status);
	List<BillDTO> findAllByUserId(Long userId);
	List<BillDTO> findAllByStatus(String status);
	BillDTO findOneByCode(String code);
	
	int sumMoneyByYearAndMonth(int year, int month);
	int sumMoneyByYearAndMonthAndDay(int year, int month, int day);
	
	int countByYearAndMonthAndStatus(int year, int month, String status);
	int countByYearAndMonthAndDayAndStatus(int year, int month, int day, String status);
	int countByYearAndStatus(int year, String status);
}
