package com.canaan.jgsf.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.apache.shiro.web.util.WebUtils;

import com.canaan.distribute.exception.BizException;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.util.SessionUtil;
import com.canaan.jgsf.util.WebUtil;
/**
 * 用于处理rest授权失败时的解决方案
 * @author zening
 * @date 2018年3月1日 上午11:13:46
 * @version 1.0.0
 */
public class ShiroRestFilter extends HttpMethodPermissionFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		boolean beLogin = SessionUtil.hasLogin(httpRequest);
		boolean beAjaxRequest = WebUtil.isAjaxRequest(httpRequest);
		if (beLogin) {
			if (beAjaxRequest) {
				throw new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION);
				//WebUtil.write(httpResponse,ResponseResult.build(new ClientBizException(ClientExceptionEnum.NO_PRIVILEGE_EXCEPTION)).json());
			} else {
				WebUtils.issueRedirect(httpRequest, httpResponse, getUnauthorizedUrl());
			}
		} else {
			if (beAjaxRequest) {
				WebUtil.write(httpResponse, ResponseResult.build(new ClientBizException(ClientExceptionEnum.USER_IS_NOT_LOGIN)).json());
			} else {
				WebUtils.issueRedirect(httpRequest, httpResponse, SystemConsts.LOGIN);
			}
		}
		
		return false;
	}

	
}
