/*   
 * Copyright (c) 2010-2020 ISCAS. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ISCAS. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ISCAS.   
 *   
 */

package com.canaan.jgsf.common;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @Description: 身份验证登录，验证失败抛出session异常，重新登录
 * @author caiyang
 * @date 2016年2月16日 上午9:54:33
 * @version V1.0
 */
public class ClientAuthenticationFilter extends FormAuthenticationFilter {
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated();
	}

	@Override
	protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
			response.setStatus(HttpStatus.OK.value());
			return false;
		}
		return super.preHandle(request, response);
	}

	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, exception);
	}

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response);
	}

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//		String currentUrl = httpServletRequest.getServletPath();// 当前请求的url
//		if (!IsAjaxCallUtil.isAjaxCall(httpServletRequest)) {
//			httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+MessageUtil.getFormatMessage("logoutUrl"));
//		} else {
//			WebUtils.toHttp(response).sendError(50004);
//		}
		return false;
	}
}
