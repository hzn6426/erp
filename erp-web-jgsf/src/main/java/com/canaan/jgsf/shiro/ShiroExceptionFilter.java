package com.canaan.jgsf.shiro;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.jgsf.exception.MVCExceptionHandler;

public class ShiroExceptionFilter extends AdviceFilter {

	
	@Override
	public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			boolean e = this.preHandle(request, response);

			if (e) {
				this.executeChain(request, response, chain);
			}

			this.postHandle(request, response);
		} catch (Exception arg8) {
			MVCExceptionHandler.doHandlerException(arg8, WebUtils.toHttp(request), WebUtils.toHttp(response));
		} finally {
//			this.cleanup(request, response, exception);
		}
	}

//	@Override
//	protected void cleanup(ServletRequest request, ServletResponse response, Exception existing)
//			throws ServletException, IOException {
//		try {
//			super.afterCompletion(request, response, existing);
//		} catch (Exception ex) {
//			MVCExceptionHandler.doHandlerException(ex, WebUtils.toHttp(request));
//		}
//	}

//	@Override
//	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
//			throws Exception {
//		if (exception != null) {
//			MVCExceptionHandler.doHandlerException(exception, WebUtils.toHttp(request), WebUtils.toHttp(response));
//		}
//	}

}
