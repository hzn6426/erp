package com.canaan.common;


public interface BaseService<E> {
	
	SearchResult<E> list(E e, int start, int limit);
	
	void save(E e);
	
	void update(E e);
	
	void delete(E e);
}
