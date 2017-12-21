package com.canaan.jgsf.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.WebRequest;

import com.google.common.base.Optional;

public class WebUtil {
	
	public static boolean isAjaxRequest(HttpServletRequest request){  
	    String header = request.getHeader("X-Requested-With");  
	    String contentType = Optional.fromNullable(request.getHeader("Content-Type")).or("");
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false 
	    		|| contentType.toLowerCase().contains("application/json");  
	    return isAjax;  
	}
	
	public static boolean isAjaxRequest(HttpHeaders header) {
		List<String> headerList = header.get("X-Requested-With");
		if (CollectionUtils.isEmpty(headerList)) {
			return false;
		}
		MediaType mediaType = header.getContentType();
		boolean isAjax = "XMLHttpRequest".equals(headerList.get(0)) ? true:false 
				|| mediaType.includes(MediaType.APPLICATION_JSON);
		return isAjax;
	}
	
	public static boolean isAjaxRequest(WebRequest request) {
		String header = request.getHeader("X-Requested-With"); 
		String contentType = Optional.fromNullable(request.getHeader("Content-Type")).or("");
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false 
	    		|| contentType.toLowerCase().contains("application/json");  
	    return isAjax;
	}
}
