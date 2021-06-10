package com.spring.controller.client.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.ReviewDTO;
import com.spring.model.MessageAlertModel;
import com.spring.service.IReviewService;

@RestController
public class ReviewAPI {

	@Autowired
	private IReviewService reviewService;

	// Get all review of product by product_id
	@GetMapping("/api/products/{id}/reviews")
	public List<ReviewDTO> getAllByProductId(@PathVariable Long id, @RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer offset) {
		if (limit != null && offset != null) {
			return reviewService.findAllByProductIdAndLimitAndOffset(id, limit, offset);

		}
		return reviewService.findAllByProductId(id);
	}

	// Get one review of product by id
	@GetMapping("/api/reviews/{id}")
	public ReviewDTO getOneById(@PathVariable Long id) {
		return reviewService.findOneById(id);
	}

	@PostMapping("/api/reviews")
	public MessageAlertModel save(@RequestBody ReviewDTO dto) {
		return reviewService.save(dto);
	}

	@PutMapping("/api/reviews")
	public MessageAlertModel update(@RequestBody ReviewDTO dto) {
		return reviewService.update(dto);
	}
	
	@DeleteMapping("/api/reviews/{ids}")
	public MessageAlertModel delete(@PathVariable Long[] ids) {
		return reviewService.delete(ids);
	}
}
