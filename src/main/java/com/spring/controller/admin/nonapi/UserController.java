package com.spring.controller.admin.nonapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.RoleDTO;
import com.spring.model.UserModel;
import com.spring.service.IRoleService;
import com.spring.utils.StatusCustom;

@Controller
public class UserController {
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = { "/admin-page/role" }, method = RequestMethod.GET)
	public String getRolePage(Model model) {
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_CATEGORY());
		model.addAttribute("ListRole", roleService.findAll());
		return "admin/role";
	}
	
	@RequestMapping(value = { "/admin-page/user" }, method = RequestMethod.GET)
	public String getUserPage(Model model, @RequestParam(value = "role", required = true) String role) {
		RoleDTO roleDTO = roleService.findOneByAlias(role);
		if( roleDTO != null) {
			model.addAttribute("Role", roleDTO);
		} else {
			return "admin/404-page";
		}
		model.addAttribute("ListStatus", StatusCustom.LIST_STATUS_OF_USER());
		return "admin/user";
	}
}
