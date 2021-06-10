package com.spring.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dto.ReviewDTO;
import com.spring.entity.ReviewEntity;
import com.spring.model.MessageAlertModel;
import com.spring.repository.ReviewRepository;
import com.spring.service.IProductService;
import com.spring.service.IReviewService;
import com.spring.utils.StatusCustom;
import com.spring.utils.WebUtils;

@Service
public class ReviewService implements IReviewService{
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public MessageAlertModel save(ReviewDTO dto) {
		ReviewEntity existReview = reviewRepository.findOneByProductIdAndUserId(dto.getProductId(), dto.getUserId());
		if(existReview != null) {
			return new MessageAlertModel("danger", "Bạn đã đánh giá cho sản phẩm này rồi!", new Date());
		}
		ReviewEntity entity = modelMapper.map(dto, ReviewEntity.class);
		entity.setStatus(StatusCustom.ACTIVE);
		try {
			reviewRepository.save(entity);
			productService.updateStarProduct(dto.getProductId());
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
		return new MessageAlertModel("success", "Gửi đánh giá thành công!", new Date());
	}

	@Override
	public MessageAlertModel update(ReviewDTO dto) {
		ReviewEntity oldReview = reviewRepository.findOneById(dto.getId());
		oldReview.setComment(dto.getComment());
		oldReview.setStar(dto.getStar());
		
		try {
			reviewRepository.saveAndFlush(oldReview);
			productService.updateStarProduct(oldReview.getProduct().getId());
		} catch (Exception e) {
			return new MessageAlertModel("danger", WebUtils.getMessageWhenErrorEntity(e.getMessage()), new Date());
		}
		return new MessageAlertModel("success", "Cập nhật thành công!", new Date());
	}

	@Override
	public List<ReviewDTO> findAllByProductIdAndLimitAndOffset(Long id, int limit, int offset) {
		List<ReviewEntity> listReviewEntity = reviewRepository.findAllByProductIdAndLimitAndOffset(id, limit, offset);
		List<ReviewDTO> listReviewDTO = new ArrayList<ReviewDTO>();
		for(ReviewEntity entity : listReviewEntity) {
			listReviewDTO.add(modelMapper.map(entity, ReviewDTO.class));
		}
		return listReviewDTO;
	}
	
	@Override
	public List<ReviewDTO> findAllByProductId(Long id) {
		List<ReviewEntity> listReviewEntity = reviewRepository.findAllByProductId(id);
		List<ReviewDTO> listReviewDTO = new ArrayList<ReviewDTO>();
		for(ReviewEntity entity : listReviewEntity) {
			listReviewDTO.add(modelMapper.map(entity, ReviewDTO.class));
		}
		return listReviewDTO;
	}

	@Override
	public ReviewDTO findOneById(Long id) {
		ReviewEntity entity = reviewRepository.findOneById(id);
		if (entity == null)
			return null;
		return modelMapper.map(entity, ReviewDTO.class);
	}

	@Override
	public MessageAlertModel delete(Long[] ids) {
		try {
			for(Long id : ids) {
				reviewRepository.delete(id);
			}
			
			return new MessageAlertModel("success", "Đã xóa!", new Date());
		} catch (Exception e) {
			return new MessageAlertModel("success", "Xóa thất bại!", new Date());
		}
		
	}

}
