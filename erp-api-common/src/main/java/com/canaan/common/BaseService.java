package com.canaan.common;

import java.util.List;

public interface BaseService<E> {
	
	SearchResult<E> list(E e, int pageNumber, int pageSize);
	
	List<E> list(E e);
	
	E get(E e);
	
	void save(E e);
	
	void update(E e);
	
	void delete(E e);
}
