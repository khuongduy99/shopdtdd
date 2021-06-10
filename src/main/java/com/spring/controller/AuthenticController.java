package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.UserDTO;
import com.spring.model.MessageAlertModel;
import com.spring.model.UserModel;
import com.spring.service.IUserService;
import com.spring.utils.SessionUtil;

@Controller
public class AuthenticController {

	 @Autowired
	 private IUserService userService;
	 

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "admin/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage() {
		
		return "admin/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(Model model,@RequestParam(value = "userName")String userName, @RequestParam(value = "fullName") String fullName, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
		UserDTO dto = new UserDTO();
		dto.setFullName(fullName);
		dto.setUserName(userName);
		dto.setEmail(email);
		dto.setPassword(password);
		MessageAlertModel message = userService.save(dto);
		if(message.getAlert().equals("danger")) {
			model.addAttribute("email", email);
			String script = "$(document).ready(function(){alert('Tài khoản đã được kích hoạt. Vui lòng đăng nhập');});";
			model.addAttribute("ScriptFormBackend", script);
			return "admin/login";
		} else {
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			
			String script = "$(document).ready(function(){$('#btn-submit').trigger('click');});";
			model.addAttribute("ScriptFormBackend", script);
		}
		
		return "admin/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/trangchu";
	}
	
	@RequestMapping(value = "/cap-nhat-tai-khoan", method = RequestMethod.GET)
	public String updateUser(HttpServletRequest request, Model model) {
		UserModel myUser = (UserModel) SessionUtil.getInstance().getValue(request, "UserModel");
		if(myUser == null) {
			return "redirect:/";
		}
		UserDTO user = userService.findOneById(myUser.getId());
		myUser.setFullName(user.getFullName());
		myUser.setAddress(user.getAddress());
		myUser.setPhone(user.getPhone());
		myUser.setGender(user.getGender());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/cap-nhat-mat-khau", method = RequestMethod.GET)
	public String updatePassword(HttpServletRequest request, Model model) {
		UserModel myUser = (UserModel) SessionUtil.getInstance().getValue(request, "UserModel");
		if(myUser == null) {
			return "redirect:/";
		}
		SessionUtil.getInstance().removeValue(request, "UserModel");
		return "redirect:/";
	}
	
	@RequestMapping(value = {"quen-mat-khau"}, method = RequestMethod.GET)
	public String getPageForgotPassword() {
		
		return "admin/forgot-password";
	}
	
	
	@RequestMapping(value = {"reset-password"}, method = RequestMethod.POST)
	public String resetPassword(Model model, @RequestParam String email, @RequestParam Long id) {
		model.addAttribute("email", email);
		model.addAttribute("id", id);
		return "admin/reset-password";
	}
	
	
}