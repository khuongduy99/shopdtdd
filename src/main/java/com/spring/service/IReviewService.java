package com.spring.service;

import java.util.List;

import com.spring.dto.ReviewDTO;
import com.spring.model.MessageAlertModel;

public interface IReviewService {
	 MessageAlertModel save(ReviewDTO dto);
	 MessageAlertModel update(ReviewDTO dto);
	 MessageAlertModel delete(Long[] id);
	 List<ReviewDTO> findAllByProductIdAndLimitAndOffset(Long id, int limit, int offset);
	 ReviewDTO findOneById(Long id);
	 
	 List<ReviewDTO> findAllByProductId(Long id);
}
