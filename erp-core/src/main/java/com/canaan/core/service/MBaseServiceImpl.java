package com.canaan.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.dozer.Mapper;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.canaan.common.MBaseService;
import com.canaan.common.SearchResult;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.exception.ExceptionEnum;
import com.canaan.core.util.Assert;
import com.canaan.core.util.CollectionMapperDecorator;
import com.canaan.distribute.util.Checker;
public abstract class MBaseServiceImpl<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> implements MBaseService<E> {

	
	
	protected abstract Columns select();
	protected abstract Wrapper<E> condition(E e);
	protected abstract OrderBy orderby();
	protected abstract Wrapper<E> primaryKeyCondition(E e);
	
	@Resource
	protected Mapper beanMapper;
	
	@Resource
	protected CollectionMapperDecorator collectionMapper;
	
	private Wrapper<E> wrapperIt(E e) {
		Assert.CheckArgument(false, e);
		Wrapper<E> wrapper = null;
		
		wrapper = condition(e);
		if (!Checker.BeNotNull(wrapper)) {
			wrapper = new EntityWrapper<E>(e);
		}
		
		Columns cmns = select();
		if (Checker.BeNotNull(cmns)) {
			wrapper.setSqlSelect(cmns);
		}
		
		
		
		return wrapper;
	}
	
	@Override
	public SearchResult<E> list(E e, int pageNumber, int pageSize) {
		
		Wrapper<E> wrapper = wrapperIt(e);
		
		Page<E> page = new Page<E>(pageNumber,pageSize);
		
		OrderBy orders = orderby();
		if (Checker.BeNotNull(orders)) {
			if (CollectionUtils.isNotEmpty(orders.getAscList())) {
				page.setAsc(orders.getAscList());
			}
			if (CollectionUtils.isNotEmpty(orders.getDescList())) {
				page.setDesc(orders.getDescList());
			}
		}
		
		List<E> list = this.baseMapper.selectPage(page, wrapper);
		int count = this.selectCount(wrapper);
		return new SearchResult<E>(count, list);
	}

	@Override
	public List<E> list(E e) {
		Wrapper<E> wrapper = wrapperIt(e);
		return this.selectList(wrapper);
	}

	@Override
	public E get(Long pk) {
		Assert.checkArgument(pk);
		return this.selectById(pk);
	}

	@Override
	public void save(E e) {
		Assert.checkArgument(e);
		this.insert(e);
	}

	@Override
	public void update(E e) {
		Assert.checkArgument(e);
		Wrapper<E> wrapper = primaryKeyCondition(e);
		Assert.CheckNotNull(wrapper,ExceptionEnum.INVALID_PK_FOR_UPDATE);
		int num = this.baseMapper.update(e, wrapper);
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_UPDATE_NUM);
	}

	@Override
	public void delete(E e) {
		Assert.checkArgument(e);
		Wrapper<E> wrapper = primaryKeyCondition(e);
		Assert.CheckNotNull(wrapper,ExceptionEnum.INVALID_PK_FOR_DELETE);
		int num = this.baseMapper.delete(wrapper);
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_DELETE_NUM);
	}

}
