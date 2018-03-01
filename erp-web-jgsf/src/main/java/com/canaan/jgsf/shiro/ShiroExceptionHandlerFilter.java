package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;

public class ShiroExceptionHandlerFilter extends AdviceFilter {

	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		try {
			super.afterCompletion(request, response, exception);
		} catch (Exception ex) {
			
		}
	}

}
