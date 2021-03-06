package com.canaan.jgsf.common;

import com.canaan.common.BaseService;
import com.canaan.common.SearchResult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * controller基类封装，针对单一对象提供增、删、改、查封装
 * @author zening
 * @date 2017年12月21日 上午9:22:46
 * @version V1.0
 */
public class BaseController<E> {
	
	@Autowired
	private BaseService<E> baseService;
	
	protected SearchResult<E> list(E e, int pageSize, int pageNumber) {
		return baseService.list(e, pageSize, pageNumber);
	}
	
	protected List<E> list(E e) {
		return baseService.list(e);
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
