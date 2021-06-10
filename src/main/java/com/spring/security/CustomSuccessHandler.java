package com.spring.security;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.model.UserModel;
import com.spring.utils.SessionUtil;


@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		
		if (response.isCommitted()) {
			return;
		}
		UserModel myUser = (UserModel) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		String targetUrl = myUser.getUrlPageRedirectLoginSuccess();
		SessionUtil.getInstance().putValue(request, "UserModel", myUser);
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}
	
//	private String determineTargetUrl(Authentication authentication) {
//		String url = "/";
//		List<String> roles = SecurityUtils.getAuthorities();
//		if (isAdmin(roles)) {
//			url = "/admin-page";
//		} else if (isUser(roles)) {
//			url = "/";
//		}
//		return url;
//	}
	
//	private boolean isAdmin(List<String> roles) {
//		if (roles.contains("admin")) {
//			return true;
//		}
//		return false;
//	}
//	
//	private boolean isUser(List<String> roles) {
//		if (roles.contains("user")) {
//			return true;
//		}
//		return false;
//	}
}
