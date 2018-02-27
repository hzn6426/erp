package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.distribute.util.UserUtil;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.util.WebUtil;
import com.canaan.util.tool.Checker;


public class AuthcAuthenticationFilter extends FormAuthenticationFilter {
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		subject.getSession().
		ShiroHttpSession session = (ShiroHttpSession)httpServletRequest.getSession();
		if (session == null) {
			return false;
		}
		// String url = httpServletRequest.getServletPath();
		// if (subject.isAuthenticated()) {
		//
		// String sessionid = httpServletRequest.getSession().getId();
		// httpServletRequest.getSession().setAttribute("sessionid", sessionid);
		// }
		// return subject.isAuthenticated();
		String contextURL = httpServletRequest.getRequestURI();
		if (isLoginRequest(request, response) 
				|| SystemConsts.ROOT_SERVLET_PATH.equals(contextURL) 
				|| !Checker.BeNotBlank(contextURL)) {
			return true;
		} 
//		session = subject.getSession(false);
//		
//		if (session == null) {
//			return false;
//		}
//		
//		String sessionMonitorValue = (String)session.getAttribute(SystemConsts.SESSION_MONITOR_KEY);
//		if (Checker.BeNotBlank(sessionMonitorValue)) {
//			return false;
//		}
//			if (WebUtils.)
		return subject.isAuthenticated() || isPermissive(mappedValue);
		
	}

	@Override
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		//清楚user缓存
		if (Checker.BeNotNull(UserUtil.get())) {
			UserUtil.remove();
		}
		super.afterCompletion(request, response, exception);
//		response.getWriter().write(ResponseResult.build(403, "没有权限").json());
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//		String currentUrl = httpServletRequest.getServletPath();// 当前请求的url
		if (WebUtil.isAjaxRequest(httpServletRequest)) {
			response.getWriter().write(ResponseResult.build(403, "没有权限").json());
//			throw new UnauthenticatedException();
//			ClientBizException cbz =  new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
//			throw cbz;
//			WebUtils.toHttp(response).sendError(cbz.getCode(), cbz.getMessage());
//			Clientbize new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
		} else {
//			WebUtils.saveRequest(httpServletRequest);//保存当前访问页面
			WebUtils.issueRedirect(httpServletRequest, response, SystemConsts.LOGIN);
		}
		return false;
	}

}
