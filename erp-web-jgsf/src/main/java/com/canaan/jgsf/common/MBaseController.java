package com.canaan.jgsf.common;

import java.io.Serializable;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.canaan.authorization.dto.UserDTO;
import com.canaan.common.MBaseService;
import com.canaan.common.SearchResult;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.util.Assert;
import com.canaan.util.tool.Checker;
/**
 * 基于mybatis plus的通用controller
 * <p>其中baseService是根据类型进行匹配，例如继承该controller中的<code>E</code>为{@link UserDTO},
 * 则service会匹配继承自{@link MBaseService}且泛型{@link UserDTO}的service</p>
 * @author zening
 * @date 2018年2月28日 上午10:52:48
 * @version 1.0.0
 */
public class MBaseController<E> extends ActionController{
	@Autowired
	private MBaseService<E> baseService;
	
	protected SearchResult<E> list(E e, int pageSize, int pageNumber) {
		
		if (Checker.BeGreaterThan(pageNumber, 50)) {
			throw new ClientBizException(ClientExceptionEnum.PAGE_SIZE_IS_TOO_LARGE_EXCEPTION, 50);
		}
		
		if (Checker.BeGreaterOrEqualThan(0, pageNumber) 
				|| Checker.BeGreaterOrEqualThan(0, pageSize)) {
			throw new ClientBizException(ClientExceptionEnum.INVALID_PAGE_PARAMS);
		}
		 
		return baseService.list(e, pageSize, pageNumber);
	}
	
	protected List<E> list(E e) {
		return baseService.list(e);
	}
	
	protected E get(Integer id) {
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
	
	protected void deleteBatch(List<? extends Serializable> ids) {
		Assert.CheckArgument(ids);
		if (Checker.BeGreaterThan(ids.size(), 50)) {
			throw new ClientBizException(ClientExceptionEnum.BATCH_SIZE_TOO_LARGE, 50);
		}
		baseService.delete(ids);
	}
}
