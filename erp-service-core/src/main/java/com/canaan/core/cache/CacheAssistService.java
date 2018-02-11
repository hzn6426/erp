package com.canaan.core.cache;


/**
 * 没有意义，仅仅用来批量删除list相关的cache
 * @author zening
 * @date 2018年2月11日 下午2:57:35
 * @version 1.0.0
 */
public interface CacheAssistService<T> {

	/**
	 * 根据对象删除对象的缓存
	 * <p>该方法不会进行任何持久化操作，仅仅是删除缓存，spel无法进行循环删除列表中对象的缓存，
	 * 需要辅助调用该方法，每次调用都会删除<code>t</code>的缓存</p>
	 * @param t 需要删除的缓存对象
	 */
	void cacheDelete(T t);
}
