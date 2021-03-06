package com.canaan.jgsf.shiro;

import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.Setter;
@SuppressWarnings({"rawtypes","unchecked"})
public class ShiroRedisCacheManager implements CacheManager {

	
	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
	
	@Setter
	private RedisTemplate redisTemplate;
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (redisTemplate == null) {
			throw new NullPointerException("null Redis Template.");
		}
		Cache<K, V> cache = cacheMap.get(name);
		if (cache == null) {
			cache = new ShiroRedisCache<K, V>(redisTemplate);
		}
		cacheMap.put(name, cache);
		return cache;
	}




	
	
}
