package com.canaan.core.cache;

import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;

public class CacheAssistServiceImpl<T> implements CacheAssistService<T> {

	@CacheDelete(@CacheDeleteKey(value="#target.getClass().getName()", hfield ="#args[0].id"))
	@Override
	public void cacheDelete(T t) {
		//不需要任何操作，仅仅是删除缓存
	}

}
