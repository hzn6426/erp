package com.canaan.jgsf.constant;

public abstract class SystemConstants {
	/*session 中存储用户信息的属性名称*/
	public static final String USER_SESSION = "USER_SESSION";
	
	/*非Ajax请求，非404错误，跳转到错误页面地址*/
	public static final String SERVER_INTERNAL_ERROR = "error500";
	
	/*非Ajax请求，404错误，跳转到错误页面地址*/
	public static final String REQUEST_NOT_FOUND = "error404";
	
	
	public static final String LOGIN = "login";
	
	public static final String INDEX = "index";
	
	
	/* 非Ajax请求，跳转到错误页面{@link #COMMON_ERROR_PAGE},会在错误页ModelAndView中将该属性绑定到错误对象*/
	public static final String ERROR_ATTRIBUTE = "ERROR_ATTRIBUTE";
}
