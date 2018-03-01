package com.canaan.jgsf.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.util.SessionUtil;
import com.canaan.jgsf.util.WebUtil;

/**
 * 用于处理authc的过滤处理
 * @author zening
 * @date 2018年3月1日 上午11:15:48
 * @version 1.0.0
 */
public class ShiroAuthcFilter extends AuthenticationFilter {
	
	private boolean doLoginAction(HttpServletRequest request, String url) {
		if (StringUtils.isEmpty(url)) {
			return false;
		}
		return url.equals(getLoginUrl()) && "POST".equalsIgnoreCase(request.getMethod());
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		Subject subject = getSubject(request, response);
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		
		String url = WebUtils.getPathWithinApplication(httpRequest);
		if ("/".equals(url) || doLoginAction(httpRequest, url)) {
			return true;
		}
		
		if (!SessionUtil.hasLogin(httpRequest)) {
			return false;
		}
		
		return subject.isAuthenticated();
		
	}


	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		
		if (WebUtil.isAjaxRequest(httpRequest)) {
			ClientBizException bizEx = new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
			WebUtil.write(httpResponse,ResponseResult.build(bizEx.getCode(), bizEx.getMessage()).json());
		} else {
//			WebUtils.saveRequest(httpServletRequest);//保存当前访问页面 --基于frame框架的不能用
			WebUtils.issueRedirect(httpRequest, response, SystemConsts.LOGIN);
		}
		return false;
	}

}
