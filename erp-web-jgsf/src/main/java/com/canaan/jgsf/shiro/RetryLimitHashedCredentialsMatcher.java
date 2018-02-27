package com.canaan.jgsf.shiro;
import java.util.concurrent.atomic.AtomicInteger;  

import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.ExcessiveAttemptsException;  
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;  
import org.apache.shiro.cache.Cache;  
import org.apache.shiro.cache.CacheManager;
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	private Cache<String, AtomicInteger> passwordRetryCache;
	private final String prefix = "retry:";
	  
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {  
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");  
    }  
  
    @Override  
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {  
        String principal = (String) token.getPrincipal();  
        String retryCacheKey = prefix + principal;
        AtomicInteger atomicInteger = passwordRetryCache.get(retryCacheKey);  
          
        if (atomicInteger == null) {  
            atomicInteger = new AtomicInteger(0); 
        }
        
//        passwordRetryCache.put(retryCacheKey, atomicInteger);
        // 如果重试次数超过5次则抛出异常  
        if (atomicInteger.incrementAndGet() > 100) {  
            throw new ExcessiveAttemptsException();  
        }  
        passwordRetryCache.put(retryCacheKey, atomicInteger);
        boolean doCredentialsMatch = super.doCredentialsMatch(token, info);// 调用父类的验证  
  
        // 如果账号密码正确，清除重试次数  
        if (doCredentialsMatch) {  
            passwordRetryCache.remove(retryCacheKey);  
        }  
  
        return doCredentialsMatch;  
  
    }  
}
