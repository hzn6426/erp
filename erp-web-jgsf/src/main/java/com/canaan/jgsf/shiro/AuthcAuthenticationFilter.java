package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.util.WebUtil;


public class AuthcAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//		if (super.isAccessAllowed(request, response, mappedValue)) return true;
		Subject subject = getSubject(request, response);
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String url = httpServletRequest.getRequestURI();
		if ("/".equals(url) || StringUtils.isEmpty(url) || isLoginSubmission(httpServletRequest, response)) return true;
		ShiroHttpSession session = (ShiroHttpSession)httpServletRequest.getSession();
		if (session == null) {
			return false;
			
		}
		ShiroUser user = (ShiroUser)session.getAttribute(SystemConsts.USER_SESSION);
		if (user == null) {
			return false;
		}
		
		return subject.isAuthenticated();
		
	}


	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
		if (WebUtil.isAjaxRequest(httpServletRequest)) {
//			throw new UnauthenticatedException("没有权限");
			httpServletResponse.getWriter().write(ResponseResult.build(403, "没有权限").json());
		} else {
//			WebUtils.saveRequest(httpServletRequest);//保存当前访问页面
			WebUtils.issueRedirect(httpServletRequest, response, SystemConsts.LOGIN);
		}
		return false;
	}

}
