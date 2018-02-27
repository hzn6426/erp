package com.canaan.jgsf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.SessionDAO;

import com.canaan.jgsf.constant.SystemConsts;

import lombok.Setter;

public class ShiroSessionListener implements SessionListener {

	@Setter
	private SessionDAO sessionDAO;
	@Override
	public void onExpiration(Session session) {
		sessionDAO.delete(session);
		session.setAttribute(SystemConsts.SESSION_MONITOR_KEY, SystemConsts.SESSION_IS_TIMEOUT);
		
	}

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop(Session session) {
		session.setAttribute(SystemConsts.SESSION_MONITOR_KEY, SystemConsts.SESSION_IS_INVALID);
		
	}

}
