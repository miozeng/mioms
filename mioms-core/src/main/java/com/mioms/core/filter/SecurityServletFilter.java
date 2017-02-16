package com.mioms.core.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityServletFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		url = url.substring(13);
		String urlReg = "/user/delete";///user/find\\S*|
		Matcher matcher = Pattern.compile(urlReg).matcher(url);
		// is url need filter
		if (matcher.matches()) {
			// check head
			String uid = request.getHeader("uid");
//			String suid = (String) session.getAttribute("uid");
			if (uid != null && uid.equals("382")) {
				chain.doFilter(request, response);
			} else {
				// head id error
				response.setHeader("Prama", "no-head");
				response.sendRedirect("/mioms-ws/login.html");
			}
		} else {
			chain.doFilter(request, response);
		}

	}
	
	public static void main(String[] args) {
		String url="/mioms-ws/user/findAll";
		url = url.substring(13);
		System.out.println(url);
		String urlReg = "/user/find\\S*|/user/delete";
		Matcher matcher = Pattern.compile(urlReg).matcher(url);
		System.out.println(matcher.matches());
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		
	}

}
