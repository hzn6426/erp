package com.canaan.jgsf.shiro;

import java.io.IOException;
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.Collections;  
import java.util.HashSet;  
import java.util.List;  
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.cache.Cache;  
import org.apache.shiro.cache.CacheException;  
import org.apache.shiro.util.CollectionUtils;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.canaan.jgsf.util.SerializeUtil;  
  
public class ShiroRedisCache<K,V> implements Cache<K,V> {  
  
    private Logger logger = LoggerFactory.getLogger(this.getClass());  
      
    private RedisTemplate<String, byte[]> redisTemplate;  
      
    private String keyPrefix = "shiro_redis_cache:";  
    
    private boolean beKeyPrimitive = false;
  
    public void setKeyPrefix(String keyPrefix) {  
        this.keyPrefix = keyPrefix;  
    }  
      
	public ShiroRedisCache(RedisTemplate<String, byte[]> template){  
         if (template == null) {
             throw new IllegalArgumentException("Cache argument cannot be null.");  
         }
         this.redisTemplate = template;  
    }  
      
	public ShiroRedisCache(RedisTemplate<String, byte[]> cache,   
                String prefix){  
           
        this( cache );  
        // set the prefix  
        this.keyPrefix = prefix;
    }  
    
    private boolean bePrimitive(K key) {
    	if (String.class.equals(key.getClass()) || Boolean.class.equals(key.getClass()) || key.getClass().isPrimitive()) {
    		if (beKeyPrimitive = false) {
    			beKeyPrimitive = true;
    		}
    		return true;
    	}
    	return false;
    }
    /** 
     * 获得byte[]型的key 
     * @param key 
     * @return 
     * @throws IOException 
     */  
    private String getBuildKey(K key) throws IOException{  
    	String theKey = null;
    	if (key == null) {
    		throw new NullPointerException("cache key is null.");
    	}
        if (bePrimitive(key)){
        	theKey = this.keyPrefix + key;  
        } else {
        	theKey = this.keyPrefix + SerializeUtil.convertToByteString(key);
        }
        return theKey;
    }  
      
    @Override  
    public V get(K key) throws CacheException {  
        logger.debug("根据key从Redis中获取对象 key [" + key + "]");  
        try {  
            if (key == null) {  
                return null;  
            }else{  
                byte[] rawValue = redisTemplate.opsForValue().get(getBuildKey(key));  
                @SuppressWarnings("unchecked")  
                V value = (V)SerializeUtil.deserialize(rawValue);  
                return value;  
            }  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
  
    }  
  
    @Override  
    public V put(K key, V value) throws CacheException {  
        logger.debug("根据key从存储 key [" + key + "]");  
         try {  
                redisTemplate.opsForValue().set(getBuildKey(key), SerializeUtil.serialize(value));  
                return value;  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
    
    public V put(K key, V value, int seconds) {
    	logger.debug("根据key从存储 key [" + key + "]");  
        try {  
        	String storeKey = getBuildKey(key);
	    	redisTemplate.opsForValue().set(storeKey, SerializeUtil.serialize(value));
	    	redisTemplate.expire(storeKey, seconds, TimeUnit.SECONDS);
	    	return value;  
       } catch (Throwable t) {  
           throw new CacheException(t);  
       }  
    }
  
    @Override  
    public V remove(K key) throws CacheException {  
        logger.debug("从redis中删除 key [" + key + "]");  
        try {  
            V previous = get(key);
            redisTemplate.delete(getBuildKey(key));  
            return previous;  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @Override  
    public void clear() throws CacheException {  
        logger.debug("从redis中删除所有元素");  
        try {  
            redisTemplate.delete(KeyPattern());
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @Override  
    public int size() {  
        try {  
            Long longSize = redisTemplate.opsForValue().size(KeyPattern());  
            return longSize.intValue();  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public Set<K> keys() {  
        try {  
            Set<String> keys = redisTemplate.keys(KeyPattern());  
            if (CollectionUtils.isEmpty(keys)) {  
                return Collections.emptySet();  
            }else{  
                Set<K> newKeys = new HashSet<K>();  
                for (String key : keys) {
                	if (beKeyPrimitive) {
                		newKeys.add((K)key);
                	} else {
                		newKeys.add((K)SerializeUtil.convertFromByteString(key.substring(keyPrefix.length())));
                	}
                }  
                return newKeys;  
            }  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
  
    @Override  
    public Collection<V> values() {  
        try {  
            Set<String> keys = redisTemplate.keys(KeyPattern());  
            if (!CollectionUtils.isEmpty(keys)) {  
                List<V> values = new ArrayList<V>(keys.size());  
                for (String key : keys) {  
                    @SuppressWarnings("unchecked")  
                    V value = get((K)key);  
                    if (value != null) {  
                        values.add(value);  
                    }  
                }  
                return Collections.unmodifiableList(values);  
            } else {  
                return Collections.emptyList();  
            }  
        } catch (Throwable t) {  
            throw new CacheException(t);  
        }  
    }  
    
    private String KeyPattern() {
    	return this.keyPrefix + "*";
    }
}  
