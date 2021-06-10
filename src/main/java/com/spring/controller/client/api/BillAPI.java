package com.spring.controller.client.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.BillDTO;
import com.spring.model.MessageAlertModel;

@RestController
public class BillAPI {
	
	 @PostMapping("/api/order")
	 public MessageAlertModel order(@Valid @RequestBody BillDTO billDTO, BindingResult bindingResult, HttpServletRequest request, Model model) {
			
			
			if(bindingResult.hasErrors()) {
				for (FieldError error : bindingResult.getFieldErrors() ) {
			       return new MessageAlertModel("danger", error.getDefaultMessage(), new Date());
			    }
			}
	
			return new MessageAlertModel("success", "Bạn có thể đặt hàng", new Date());
		}
	 
}
