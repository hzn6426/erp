package com.canaan.jgsf.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;

public class WebUtil {
	
	public static boolean isAjaxRequest(HttpServletRequest request){  
	    String header = request.getHeader("X-Requested-With");  
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
	    return isAjax;  
	}
	
	public static boolean isAjaxRequest(HttpHeaders header) {
		List<String> headerList = header.get("X-Requested-With");
		if (CollectionUtils.isEmpty(headerList)) {
			return false;
		}
		boolean isAjax = "XMLHttpRequest".equals(headerList.get(0)) ? true:false;
		return isAjax;
	}
	
	public static boolean isAjaxRequest(WebRequest request) {
		String header = request.getHeader("X-Requested-With");  
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
	    return isAjax;
	}
}
