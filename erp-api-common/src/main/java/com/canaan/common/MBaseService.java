package com.canaan.common;

import java.io.Serializable;
import java.util.List;

public interface MBaseService<E> {
	
	SearchResult<E> list(E e, int pageNumber, int pageSize);
	
	List<E> list(E e);
	
	E get(Integer pk);
	
	void save(E e);
	
	void update(E e);
	
	void delete(E e);
	
	void delete(List<? extends Serializable> ids);
}
