package com.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {
		"/member/join",
		"/member/login",
		"/member/pwdCheck",
		"/member/modPwd"
})
public class EncriptFilter extends HttpFilter implements Filter {
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		EncryptWrapper ew = new EncryptWrapper((HttpServletRequest)request);
		
		if(request.getParameter("pwd")!=null) {
			request.setAttribute("pwd", ew.getParameter("pwd"));
		}
		chain.doFilter(request, response);
	}
}