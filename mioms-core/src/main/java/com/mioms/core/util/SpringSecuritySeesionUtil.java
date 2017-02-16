package com.mioms.core.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mioms.core.dto.UserDetail;

public class SpringSecuritySeesionUtil {
	
	public static UserDetail findCurrentUser(){
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication s= context.getAuthentication();
		Object a = s.getPrincipal();
		Object b = s.getDetails();
		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		return userDetail;
	}
	
	public static String getMd5Passwords(String password, String name){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();  
		String npassword = md5.encodePassword(password, name);
		return npassword;
	}
	

}
