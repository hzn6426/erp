package com.canaan.jgsf.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.Validate;

import com.canaan.jgsf.shiro.ShiroUser;

public class SessionUtil {

	private static final String USER_SESSION_ATTRIBUTE = "_SYSTEM_SESSION_USER";
	
	public static void saveUser(HttpServletRequest request, ShiroUser user) {
			Validate.notNull(request, "request can not be null.");
			Validate.notNull(user, "user can not be null.");
			request.getSession().setAttribute(USER_SESSION_ATTRIBUTE, user);
	}
	
	public static ShiroUser loadUser(HttpServletRequest request) {
		Validate.notNull(request, "request can not be null.");
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		return (ShiroUser)session.getAttribute(USER_SESSION_ATTRIBUTE);
	}
	
	public static void removeUser(HttpServletRequest request) {
		Validate.notNull(request, "request can not be null.");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(USER_SESSION_ATTRIBUTE);
		}
	}
	
	public static boolean hasLogin(HttpServletRequest request) {
		return loadUser(request) != null;
	}
	

}
