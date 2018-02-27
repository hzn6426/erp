package com.canaan.jgsf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;


import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author zening
 * @date 2017年11月20日 下午2:57:02
 * @version V1.0
 */
public class ShiroSessionDAO extends CachingSessionDAO {
	private static Logger logger = LoggerFactory.getLogger(ShiroSessionDAO.class);
	private static final String SESSION_KEY_PREFIX = "jgsf_shiro_redis_session:";
	private final int sessionTimeoutSeconds;
	private final RedisTemplate<String, Session> redisTemplate;
	public ShiroSessionDAO(RedisTemplate<String, Session> redisTemplate, int sessionTimeoutSeconds) {
		this.redisTemplate = redisTemplate;
		this.sessionTimeoutSeconds = sessionTimeoutSeconds;
	}

	@Override
	protected Serializable doCreate(Session session) {
		if(session == null){
			logger.error("Session to create must not be null!");
			return null;
		}
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		putSession2Redis(buildRedisSessionKey(sessionId), sessionTimeoutSeconds, session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("SessionId of session to read must not be null!");
			return null;
		}
		return getSessionFRedis(buildRedisSessionKey(sessionId));
	}

//	@Override
//	public void update(Session session) throws UnknownSessionException {
//		if(session == null || session.getId() == null){
//			logger.error("Session or SessionId to update must not be null!");
//			return;
//		}
//		putSession2Redis(buildRedisSessionKey(session.getId()), sessionTimeoutSeconds, session);
//	}

//	@Override
//	public void delete(Session session) {
//		if(session == null || session.getId() == null){
//			logger.error("Session or SessionId to delete must not be null!");
//			return;
//		}
//		deleteSessionFRedis(buildRedisSessionKey(session.getId()));
//	}

	@Override
	public Collection<Session> getActiveSessions() {
		
		return querySessionByKeyPatterns(buildRedisSessionKeyPattern());
	}

	private String buildRedisSessionKey(Serializable sessionId) {
		return SESSION_KEY_PREFIX + sessionId;
	}

	private String buildRedisSessionKeyPattern() {
		return SESSION_KEY_PREFIX + "*";
	}
	
	protected byte[] getKeyBytes(String k) {
		return (SESSION_KEY_PREFIX + k).getBytes();
	}
	
	private Set<Session> querySessionByKeyPatterns(String patterns) {
		Set<String> keys = redisTemplate.keys(patterns);
		if (CollectionUtils.isEmpty(keys)) {
			return Collections.emptySet();
		}
		Set<Session> results = new HashSet<>();
		for (String key : keys) {
			results.add(redisTemplate.opsForValue().get(key));
		}
		return results;
	}
	private void putSession2Redis(String key,int seconds, Session session) {
		redisTemplate.opsForValue().set(key, session, seconds, TimeUnit.SECONDS);
	}
	private Session getSessionFRedis(String key) {
		return redisTemplate.opsForValue().get(key);
	}
	private void deleteSessionFRedis(String key) {
		redisTemplate.delete(key);
	}

	@Override
	protected void doUpdate(Session session) {
		
		if(session == null || session.getId() == null){
			logger.error("Session or SessionId to update must not be null!");
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("do update session to redis, the session id is {}",session.getId());
		}
		if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {    
            return;    
        }
		//如果没有改变不更新缓存，直接返回
		if (ShiroSession.class.isInstance(session)) {
			ShiroSession ss = (ShiroSession) session;
			if (!ss.isChanged()) {
				return;
			}
		}
		putSession2Redis(buildRedisSessionKey(session.getId()), sessionTimeoutSeconds, session);
	}

	@Override
	protected void doDelete(Session session) {
		if(session == null || session.getId() == null){
			logger.error("Session or SessionId to delete must not be null!");
			return;
		}
		deleteSessionFRedis(buildRedisSessionKey(session.getId()));
	}
}
