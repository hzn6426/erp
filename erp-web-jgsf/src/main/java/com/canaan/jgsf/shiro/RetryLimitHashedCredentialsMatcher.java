package com.canaan.jgsf.shiro;
import java.util.concurrent.atomic.AtomicInteger;  

import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.ExcessiveAttemptsException;  
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;  
import org.apache.shiro.cache.Cache;  
import org.apache.shiro.cache.CacheManager;

import lombok.Setter;
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	private ShiroRedisCache<String, AtomicInteger> passwordRetryCache;
	private String retryPrefix = "retry:";//存储的重试次数前缀
	@Setter
	private int retryTime = 5; //重试次数
	@Setter
	private int retryTimeout = 1800000;//超过重试次数锁定半小时
	  
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {  
        Cache<String, AtomicInteger> cache = cacheManager.getCache("passwordRetryCache");
        passwordRetryCache = (ShiroRedisCache<String, AtomicInteger>) cache;
    }  
  
    @Override  
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {  
        String principal = (String) token.getPrincipal();  
        String retryCacheKey = retryPrefix + principal;
        AtomicInteger atomicInteger = passwordRetryCache.get(retryCacheKey);  
        if (atomicInteger == null) {  
            atomicInteger = new AtomicInteger(0); 
        }
        
//        passwordRetryCache.put(retryCacheKey, atomicInteger);
        // 如果重试次数超过5次则抛出异常  
        if (atomicInteger.incrementAndGet() > retryTime) { 
            throw new ExcessiveAttemptsException();  
        }
        passwordRetryCache.put(retryCacheKey, atomicInteger, retryTimeout);
        boolean doCredentialsMatch = super.doCredentialsMatch(token, info);// 调用父类的验证  
  
        // 如果账号密码正确，清除重试次数  
        if (doCredentialsMatch) {  
            passwordRetryCache.remove(retryCacheKey);  
        }  
  
        return doCredentialsMatch;  
  
    }  
}
