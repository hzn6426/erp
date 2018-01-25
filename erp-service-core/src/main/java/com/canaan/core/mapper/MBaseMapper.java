package com.canaan.core.mapper;

import java.io.Serializable;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jarvis.cache.annotation.Cache;

public interface MBaseMapper<T> extends BaseMapper<T> {

	Integer insert(T entity);

	Integer deleteById(Serializable id);

	@Cache(expire = 3600, expireExpression = "null == #retVal ? 60: 3600", key = "eee")
	T selectById(Serializable id);

	Integer delete(Wrapper<T> wrapper);

	Integer update(T entity, Wrapper<T> wrapper);
	
}
