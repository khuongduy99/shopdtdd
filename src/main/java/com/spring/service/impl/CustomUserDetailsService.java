package com.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.RoleEntity;
import com.spring.entity.UserEntity;
import com.spring.model.UserModel;
import com.spring.repository.UserRepository;
import com.spring.utils.WebUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String [] splitUserNameAndUrlLoginSuccess = username.split(WebUtils.DELIMETER);
		username = splitUserNameAndUrlLoginSuccess[0];
		UserEntity userEntity = userRepository.findOneByEmail(username);
		
		if (userEntity == null) {
			userEntity = userRepository.findOneByUserName(username);
			if (userEntity == null) {
				userEntity = userRepository.findOneByUserName(username);
				throw new UsernameNotFoundException("Tài khoản hoặc mật khẩu không đúng!");
			}
			
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role: userEntity.getListRole()) {
			authorities.add(new SimpleGrantedAuthority(role.getAlias()));
		}
		UserModel myUser = new UserModel(userEntity.getEmail(), userEntity.getPassword(), 
							true, true, true, true, authorities);
		myUser.setFullName(userEntity.getFullName());
		myUser.setAddress(userEntity.getAddress());
		myUser.setGender(userEntity.getGender());
		myUser.setPhone(userEntity.getPhone());
		myUser.setEmail(userEntity.getEmail());
		myUser.setId(userEntity.getId());
		myUser.setUserName(userEntity.getUserName());
		if(splitUserNameAndUrlLoginSuccess.length > 1) {
			myUser.setUrlPageRedirectLoginSuccess(splitUserNameAndUrlLoginSuccess[1]);
		} else {
			myUser.setUrlPageRedirectLoginSuccess("/");
		}
		List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authority : authorities) {
        	roles.add(authority.getAuthority());
        }
        if(isAdmin(roles)) myUser.setAdmin(true);
		return myUser;
	}
	
	private boolean isAdmin(List<String> roles) {
		if (roles.contains("admin")) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		String username = "vcctvt";
		System.out.println(username.split("2410dt1408")[0]);
	}
}