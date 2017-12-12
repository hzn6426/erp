package com.canaan.jgsf.common;

import com.canaan.common.BaseService;
import com.canaan.common.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;

import com.canaan.common.BaseModel;

public class BaseController<E extends BaseModel> {
	
	@Autowired
	private BaseService<E> baseService;
	
	protected SearchResult<E> list(E e, int start, int limit) {
		return baseService.list(e, start, limit);
	}
	
	protected void save(E e) {
		baseService.save(e);
	}
}
