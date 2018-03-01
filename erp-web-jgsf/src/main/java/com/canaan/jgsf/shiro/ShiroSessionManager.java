package com.canaan.jgsf.shiro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.canaan.util.tool.LoopUtil;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShiroSessionManager extends DefaultWebSessionManager {

	@Setter
	private List<String> staticResourceUrls = new ArrayList<>();
	
//	private final ConcurrentHashMap<String, ShiroSession> map = new ConcurrentHashMap<>();
	//缓存session，避免重复调用
	@Override
	protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
		Serializable sessionId = getSessionId(sessionKey);
		ServletRequest request = null;
		String url = "";
		if (sessionId == null) return null;
		
		if (sessionKey instanceof WebSessionKey) {
			request = ((WebSessionKey) sessionKey).getServletRequest();
			HttpServletRequest httpRequest = WebUtils.toHttp(request);
			url = httpRequest.getRequestURI();
			log.info("============================URL is {}",url);
			if (doUrlMatch(url)) {
				return null;
			}
		}
//		if (request != null && null != sessionId) {
//			Object sessionObj = request.getAttribute(sessionId.toString());
//			if (sessionObj != null) {
//				return (Session) sessionObj;
//			}
//		}
		if (log.isDebugEnabled()) {
			log.debug("get session cache from redis, the url is {}",url);
		}
		
		Session session = super.retrieveSession(sessionKey);
//		if (request != null && null != sessionId) {
//			request.setAttribute(sessionId.toString(), session);
//		}
		return session;
//		return super.retrieveSession(sessionKey);
	}
	
	/**
	 * 静态资源url匹配。
	 * <p>匹配规则：</p>
	 * <ul>
	 * <li>?  匹配一个字符</li>
	 * <li>*  匹配一个或多个字符</li>
	 * <li>** 匹配一个或多个目录</li>
	 * </ul>
	 * @param url 需要匹配的url
	 * @return 任意匹配为true
	 */
	private boolean doUrlMatch(String url) {
		
		if (StringUtils.isEmpty(url)) return false;
		PathMatcher matcher = new AntPathMatcher();
		Predicate<String> matchPredicate = new Predicate<String>() {
			@Override
			public boolean evaluate(String surl) {
				return matcher.match(surl, url);
			}
		};
		String matchUrl = LoopUtil.find(staticResourceUrls, matchPredicate);
		return StringUtils.isNotBlank(matchUrl);
	}

}
