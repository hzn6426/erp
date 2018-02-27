package com.canaan.jgsf.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import com.canaan.jgsf.constant.SystemConsts;

public class ShiroSessionManager extends DefaultWebSessionManager {

//	@Override
//	protected void onInvalidation(Session session, InvalidSessionException ise, SessionKey key) {
//		// TODO Auto-generated method stub
//		super.onInvalidation(session, ise, key);
//	}
//	//验证session是否已过期
//	@Override
//	protected void validate(Session session, SessionKey key) throws InvalidSessionException {
//		try {
//			super.validate(session, key);
//		} catch (ExpiredSessionException ese) {
////			this.delete(session);
//			session.setAttribute(SystemConsts.SESSION_MONITOR_KEY, SystemConsts.SESSION_IS_TIMEOUT);
//			WebSessionKey sessionKey = null;
//			if (WebSessionKey.class.isInstance(key)) {
//				sessionKey = (WebSessionKey)key;
//			}
//			if (sessionKey != null) {
//				HttpServletRequest request = (HttpServletRequest)sessionKey.getServletRequest();
//				HttpServletResponse response = (HttpServletResponse)sessionKey.getServletResponse();
//				this.getSessionIdCookie().removeFrom(request, response);
//			}
//		} catch (InvalidSessionException ise) {
//			session.setAttribute(SystemConsts.SESSION_MONITOR_KEY, SystemConsts.SESSION_IS_INVALID);
////			session
////			session.
////			this.delete(session);
//		} 
//	}

	//处理redis session被删除导致UnknownSessionException问题
//	@Override
//	public Session getSession(SessionKey key) throws SessionException {
//		Session session = null;
//		WebSessionKey sessionKey = null;
//		try {
//			if (WebSessionKey.class.isInstance(key)) {
//				sessionKey = (WebSessionKey)key;
//			}
//			session = super.getSession(key);
//		} catch (Exception use) {
//			
//			//invalid session cookie
////			WebUtils
//			if (sessionKey != null) {
//				HttpServletRequest request = (HttpServletRequest)sessionKey.getServletRequest();
//				HttpServletResponse response = (HttpServletResponse)sessionKey.getServletResponse();
//				this.getSessionIdCookie().removeFrom(request, response);
//			}
//		}
//		return session;
//	}


	


}
