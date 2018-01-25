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

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.canaan.distribute.util.Checker;
import com.canaan.distribute.util.UserUtil;
import com.canaan.jgsf.constant.SystemConstants;


/**
 * @Description: 身份验证登录，验证失败抛出session异常，重新登录
 * @author caiyang
 * @date 2016年2月16日 上午9:54:33
 * @version V1.0
 */
public class RememberMeAuthenticationFilter extends FormAuthenticationFilter {
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		Session session = subject.getSession();
		boolean beRememberMe = !subject.isAuthenticated() && subject.isRemembered() 
				&& !Checker.BeNotNull(session.getAttribute(SystemConstants.USER_SESSION));
		if (beRememberMe) {
//			String userName = (String)subject.getPrincipal();
			//set user session
		}
		return subject.isAuthenticated() || subject.isRemembered();
	}


	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		//清楚user缓存
		if (Checker.BeNotNull(UserUtil.get())) {
			UserUtil.remove();
		}
		super.afterCompletion(request, response, exception);
	}


	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		return false;
	}
}
