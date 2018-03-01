/*   
 * Copyright (c) 2010-2020 ISCAS. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * ISCAS. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with ISCAS.   
 *   
 */

package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.util.WebUtil;


/**
 * 身份验证登录，验证失败抛出session异常，重新登录
 * @author zening
 * @date 2018年2月22日 下午1:51:48
 * @version 1.0.0
 */
public class ShiroRememberMeFilter extends AuthenticationFilter {
	
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		return subject.isAuthenticated() || subject.isRemembered();
	}


	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		if (WebUtil.isAjaxRequest(httpServletRequest)) {
			throw new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
		} else {
//			WebUtils.saveRequest(httpServletRequest);
			WebUtils.issueRedirect(httpServletRequest, response, SystemConsts.LOGIN);
		}
		return true;
	}
}
