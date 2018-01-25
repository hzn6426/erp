package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;

import com.canaan.distribute.util.Checker;
import com.canaan.distribute.util.UserUtil;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.util.WebUtil;


public class AuthcAuthenticationFilter extends AuthenticationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
//		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// String url = httpServletRequest.getServletPath();
		// if (subject.isAuthenticated()) {
		//
		// String sessionid = httpServletRequest.getSession().getId();
		// httpServletRequest.getSession().setAttribute("sessionid", sessionid);
		// }
		// return subject.isAuthenticated();
		return subject.isAuthenticated();
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

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//		String currentUrl = httpServletRequest.getServletPath();// 当前请求的url
		if (WebUtil.isAjaxRequest(httpServletRequest)) {
			throw new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
		}
		return false;
	}
}
