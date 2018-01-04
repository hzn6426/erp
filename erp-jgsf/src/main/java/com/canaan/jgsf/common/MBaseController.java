package com.canaan.jgsf.common;

import java.util.List;

import javax.annotation.Resource;

import com.canaan.common.MBaseService;
import com.canaan.common.SearchResult;

public class MBaseController<E> {

	@Resource
	private MBaseService<E> baseService;
	
	protected SearchResult<E> list(E e, int pageSize, int pageNumber) {
		return baseService.list(e, pageSize, pageNumber);
	}
	
	protected List<E> list(E e) {
		return baseService.list(e);
	}
	
	protected E get(Long id) {
		return baseService.get(id);
	}
	
	protected void save(E e) {
		baseService.save(e);
	}
	
	protected void update(E e) {
		baseService.update(e);
	}
	
	protected void delete(E e) {
		baseService.delete(e);
	}
}
