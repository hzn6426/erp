package com.canaan.core.cache;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import com.jarvis.cache.ICacheManager;
import com.jarvis.cache.exception.CacheCenterConnectionException;
import com.jarvis.cache.serializer.ISerializer;
import com.jarvis.cache.serializer.StringSerializer;
import com.jarvis.cache.to.CacheKeyTO;
import com.jarvis.cache.to.CacheWrapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class RedisClusterCacheManager implements ICacheManager {

	private static final StringSerializer KEY_SERIALIZER=new StringSerializer();

    private final ISerializer<Object> serializer;

    @Setter
    private RedisTemplate<byte[], byte[]> redisTemplate;
//    private JedisCluster jedisCluster;

    /**
     * Hash的缓存时长：等于0时永久缓存；大于0时，主要是为了防止一些已经不用的缓存占用内存;hashExpire小于0时，则使用@Cache中设置的expire值（默认值为-1）。
     */
    private int hashExpire=-1;

    /**
     * 是否通过脚本来设置 Hash的缓存时长
     */
    private boolean hashExpireByScript=true;

    public RedisClusterCacheManager(ISerializer<Object> serializer) {
        this.serializer=serializer;
    }

    @Override
    public void setCache(final CacheKeyTO cacheKeyTO, final CacheWrapper<Object> result, final Method method, final Object args[]) throws CacheCenterConnectionException {
        if(null == redisTemplate || null == cacheKeyTO) {
            return;
        }
        String cacheKey=cacheKeyTO.getCacheKey();
        if(null == cacheKey || cacheKey.length() == 0) {
            return;
        }
        try {
            int expire=result.getExpire();
            String hfield=cacheKeyTO.getHfield();
            if(null == hfield || hfield.length() == 0) {
                if(expire == 0) {
                	redisTemplate.opsForValue().set(KEY_SERIALIZER.serialize(cacheKey), serializer.serialize(result));
                } else if(expire > 0) {
                	redisTemplate.opsForValue().set(KEY_SERIALIZER.serialize(cacheKey), serializer.serialize(result), expire, TimeUnit.SECONDS);
                }
            } else {
                hashSet(cacheKey, hfield, result);
            }
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
        }
    }

//    private static byte[] hashSetScript;
    private static String hashSetScript;
    static {
    	hashSetScript = "redis.call('HSET', KEYS[1], ARGV[1], ARGV[2]);\nredis.call('EXPIRE', KEYS[1], tonumber(ARGV[3]));";
//        try {
//            String tmpScript="redis.call('HSET', KEYS[1], ARGV[1], ARGV[2]);\nredis.call('EXPIRE', KEYS[1], tonumber(ARGV[3]));";
//            hashSetScript=tmpScript.getBytes("UTF-8");
//        } catch(UnsupportedEncodingException ex) {
//            log.error(ex.getMessage(), ex);
//        }
    }

    private void hashSet(String cacheKey, String hfield, CacheWrapper<Object> result) throws Exception {
        byte[] key=KEY_SERIALIZER.serialize(cacheKey);
        byte[] field=KEY_SERIALIZER.serialize(hfield);
        byte[] val=serializer.serialize(result);
        int hExpire;
        if(hashExpire < 0) {
            hExpire=result.getExpire();
        } else {
            hExpire=hashExpire;
        }
        if(hExpire == 0) {
        	redisTemplate.opsForHash().put(key, field, val);
        } else if(hExpire > 0) {
            if(hashExpireByScript) {
                List<byte[]> keys=new ArrayList<byte[]>();
                keys.add(key);

                List<byte[]> args=new ArrayList<byte[]>();
                args.add(field);
                args.add(val);
                args.add(KEY_SERIALIZER.serialize(String.valueOf(hExpire)));
                DefaultRedisScript<Void> script = new DefaultRedisScript<>();
                script.setScriptText(hashSetScript);
                redisTemplate.execute(script , keys, args);
            } else {
            	redisTemplate.opsForHash().put(key, field, val);
            	redisTemplate.expire(key, hExpire, TimeUnit.SECONDS);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public CacheWrapper<Object> get(final CacheKeyTO cacheKeyTO, final Method method, final Object args[]) throws CacheCenterConnectionException {
        if(null == redisTemplate || null == cacheKeyTO) {
            return null;
        }
        String cacheKey=cacheKeyTO.getCacheKey();
        if(null == cacheKey || cacheKey.length() == 0) {
            return null;
        }
        CacheWrapper<Object> res=null;
        try {
            byte bytes[]=null;
            String hfield=cacheKeyTO.getHfield();
            if(null == hfield || hfield.length() == 0) {
                bytes=redisTemplate.opsForValue().get(KEY_SERIALIZER.serialize(cacheKey));
            } else {
                bytes=(byte[])redisTemplate.opsForHash().get(KEY_SERIALIZER.serialize(cacheKey), KEY_SERIALIZER.serialize(hfield));
            }
            Type returnType=null;
            if(null != method) {
                returnType=method.getGenericReturnType();
            }
            res=(CacheWrapper<Object>)serializer.deserialize(bytes, returnType);
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
        }
        return res;
    }

    /**
     * 根据缓存Key删除缓存
     * @param cacheKeyTO 缓存Key
     */
    @Override
    public void delete(CacheKeyTO cacheKeyTO) throws CacheCenterConnectionException {
        if(null == redisTemplate || null == cacheKeyTO) {
            return;
        }
        String cacheKey=cacheKeyTO.getCacheKey();
        if(null == cacheKey || cacheKey.length() == 0) {
            return;
        }
        log.debug("delete cache:{}", cacheKey);
        try {
            String hfield=cacheKeyTO.getHfield();
            if(null == hfield || hfield.length() == 0) {
            	redisTemplate.delete(KEY_SERIALIZER.serialize(cacheKey));
            } else {
            	redisTemplate.opsForHash().delete(KEY_SERIALIZER.serialize(cacheKey), KEY_SERIALIZER.serialize(hfield));
            }
        } catch(Exception ex) {
            log.error(ex.getMessage(), ex);
        } finally {
        }
    }


    public int getHashExpire() {
        return hashExpire;
    }

    public void setHashExpire(int hashExpire) {
        if(hashExpire < 0) {
            return;
        }
        this.hashExpire=hashExpire;
    }

    public boolean isHashExpireByScript() {
        return hashExpireByScript;
    }

    public void setHashExpireByScript(boolean hashExpireByScript) {
        this.hashExpireByScript=hashExpireByScript;
    }

}
