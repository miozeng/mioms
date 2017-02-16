package com.mioms.core.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mioms.core.dao.UserDao;
import com.mioms.core.entity.User;

public class EpreciousCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	private UserDao userDao;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
      LdapUserDetailsImpl  authUser = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String name = authUser.getUsername(); //get logged in username

      User user = userDao.findUniqueByProperty("name", name);
		//is user exist in database
      if(user == null){
    	  response.sendRedirect("/mioms-ws/login.html?error=true");  
      }else {
    	  response.sendRedirect("/mioms-ws/index.html");  
      }
	}

}
