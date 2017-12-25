package com.canaan.common;


public interface BaseService<E> {
	
	SearchResult<E> list(E e, int pageNumber, int pageSize);
	
	E get(E e);
	
	void save(E e);
	
	void update(E e);
	
	void delete(E e);
}
