package com.canaan.jgsf.common;

import com.canaan.common.BaseService;
import com.canaan.common.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;

import com.canaan.common.BaseModel;
/**
 * controller基类封装，针对单一对象提供增、删、改、查封装
 * @author zening
 * @date 2017年12月21日 上午9:22:46
 * @version V1.0
 */
public class BaseController<E extends BaseModel> {
	
	@Autowired
	private BaseService<E> baseService;
	
	protected SearchResult<E> list(E e, int start, int limit) {
		return baseService.list(e, start, limit);
	}
	
	protected E get(E e) {
		return baseService.get(e);
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
