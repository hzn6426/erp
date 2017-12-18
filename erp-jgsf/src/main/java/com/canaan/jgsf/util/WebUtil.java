package com.canaan.jgsf.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	public static boolean isAjaxRequest(HttpServletRequest request){  
	    String header = request.getHeader("X-Requested-With");  
	    boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
	    return isAjax;  
	}
}
