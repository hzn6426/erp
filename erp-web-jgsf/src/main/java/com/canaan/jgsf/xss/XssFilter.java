package com.canaan.jgsf.xss;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Setter;

public class XssFilter extends OncePerRequestFilter {

	//需要排除的url
	@Setter
	private List<String> exclusions = null;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String servletPath = request.getServletPath();
		if (exclusions != null && exclusions.contains(servletPath)) {
			filterChain.doFilter(request, response);
        } else {
        	filterChain.doFilter(new XssHttpServletRequestWrapper(request), response);
        }
		
	}

	
}
