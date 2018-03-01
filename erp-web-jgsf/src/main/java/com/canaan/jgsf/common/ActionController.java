package com.canaan.jgsf.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 封装spring mvc通用获取request,response,session等方法
 * @author zening
 * @date 2018年2月28日 上午10:51:56
 * @version 1.0.0
 */
public class ActionController {
	
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	protected HttpServletResponse getResponse() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	protected Object getRequestAttribute(String name) {
		return getRequest().getAttribute(name);
	}
	
	protected Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}
	
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	protected String getRequestParameter(String name) {
		return getRequest().getParameter(name);
	}
}
