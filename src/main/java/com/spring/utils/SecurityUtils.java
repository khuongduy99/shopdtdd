package com.spring.utils;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.model.UserModel;


public class SecurityUtils {
	
	public static UserModel getPrincipal() {
		try {
			UserModel myUser = (UserModel) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
			  return myUser;
		} catch (Exception e) {
		}
		
        return null;
    }
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<String>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
		return results;
	}
}