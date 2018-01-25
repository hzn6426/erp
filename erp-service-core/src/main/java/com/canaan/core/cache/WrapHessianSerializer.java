package com.canaan.core.cache;

import java.lang.reflect.Type;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.jarvis.cache.serializer.ISerializer;

public class WrapHessianSerializer<T>  implements RedisSerializer<T> {

	ISerializer<T> serializer;
	private Type type;
	
	public WrapHessianSerializer(ISerializer<T> serializer) {
		this.serializer = serializer;
		//获取泛型参数的Class类型
		type = getClass().getGenericSuperclass();  
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		try {
			return serializer.serialize(t);
		} catch (Exception e) {
			throw new SerializationException(e.getMessage(),e);
		}
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		try {
			return serializer.deserialize(bytes, type);
		} catch (Exception e) {
			throw new SerializationException(e.getMessage(),e);
		}
	}
	
	

	

}
