package com.canaan.core.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.canaan.common.MBaseService;
import com.canaan.common.SearchResult;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.common.BaseModel;
import com.canaan.core.exception.ExceptionEnum;
import com.canaan.core.util.Assert;
import com.canaan.core.util.CollectionMapperDecorator;
import com.canaan.distribute.util.Checker;
import com.google.common.collect.Lists;
import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.CacheDelete;
import com.jarvis.cache.annotation.CacheDeleteKey;
import com.jarvis.cache.annotation.CacheDeleteTransactional;

/**
 * 通用基于mybatis-plus的服务方法基类
 * <ul>
 * 	<li>T是数据库对应的实体对象</li>
 * 	<li>M是实体对象对应的Mapper</li>
 * 	<li>V是传过来的DTO对象</li>
 * </ul>
 * @author zening
 * @date 2018年1月5日 上午11:54:53
 * @version V1.0
 */
public abstract class MBaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel, V> extends ServiceImpl<M, T>  implements MBaseService<V> {
	
	protected  Class<T> entityClassType;
	
	protected Class<V> vmodelClassType;
	
	@SuppressWarnings("unchecked")
	public  MBaseServiceImpl() {
		//获取泛型参数的Class类型
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClassType = (Class<T>) params[1];
        vmodelClassType = (Class<V>) params[2];
//        entityClassName = entityClassType.getName();
	}
	protected abstract Columns select();
	protected abstract Wrapper<T> condition(V v);
	protected abstract OrderBy orderby();
	protected abstract Wrapper<T> primaryKeyCondition(V v);
	
	@Resource
	protected Mapper beanMapper;
	
	@Resource
	protected CollectionMapperDecorator collectionMapper;
	
	/**
	 * 将 {@link V}类型对象(<code>dto</code>)转化成{@link T}类型对象(<code>entity</code>)
	 * @param v dto对象
	 * @return entity对象
	 */
	protected T mapper(V v) {
		Assert.checkArgument(v);
		return beanMapper.map(v, entityClassType);
	}
	
	/**
	 * 将{@link T} 类型对象(<code>entity</code>)转化成 {@link V} 类型对象(<code>dto</code>)
	 * @param t entity对象
	 * @return dto对象
	 */
	protected V mapper(T t) {
		Assert.checkArgument(t);
		return beanMapper.map(t, vmodelClassType);
	}
	
	/**
	 * 将{@link T} 类型对象(<code>entity</code>)集合转化成 {@link V} 类型对象(<code>dto</code>)集合
	 * @param tlist
	 * @return
	 */
	protected List<V> mapper(List<T> tlist) {
		Assert.checkArgument(tlist);
		return Lists.newArrayList(collectionMapper.mapCollection(tlist, vmodelClassType));
	}
	
	/**
	 * 封装<code>select</code>部分和<code>where</code>部分
	 * @param v
	 * @return
	 */
	private Wrapper<T> wrapperIt(V v) {
		Assert.CheckArgument(false, v);
		Wrapper<T> wrapper = null;
		
		wrapper = condition(v);
		if (!Checker.BeNotNull(wrapper)) {
			wrapper = new EntityWrapper<T>();
		}
		
		Columns cmns = select();
		if (Checker.BeNotNull(cmns)) {
			wrapper.setSqlSelect(cmns);
		}
		
		return wrapper;
	}
	
	@Override
	public SearchResult<V> list(V v, int pageNumber, int pageSize) {
		
		Wrapper<T> wrapper = wrapperIt(v);
		
		Page<T> page = new Page<T>(pageNumber,pageSize);
		
		OrderBy orders = orderby();
		if (Checker.BeNotNull(orders)) {
			if (CollectionUtils.isNotEmpty(orders.getAscList())) {
				page.setAscs(orders.getAscList());
			}
			if (CollectionUtils.isNotEmpty(orders.getDescList())) {
				page.setDescs(orders.getDescList());
			}
		}
		
		List<T> list = this.baseMapper.selectPage(page, wrapper);
		List<V> vlist = Lists.newArrayList(collectionMapper.mapCollection(list, vmodelClassType));
		int count = this.baseMapper.selectCount(wrapper);
		return new SearchResult<V>(count, vlist);
	}

	@Override
	public List<V> list(V v) {
		Wrapper<T> wrapper = wrapperIt(v);
		List<T> list = this.baseMapper.selectList(wrapper);
		return Lists.newArrayList(collectionMapper.mapCollection(list, vmodelClassType));
	}

	@Cache(expire = 300, expireExpression = "null == #retVal ? 300: 3600",  key = "#target.getClass().getName() + '-' + #args[0]")
	@Override
	public V get(Long pk) {
		Assert.checkArgument(pk);
		T t =  this.baseMapper.selectById(pk);
		return beanMapper.map(t, vmodelClassType);
	}
	
	@Override
	public void save(V v) {
		Assert.checkArgument(v);
		T t = beanMapper.map(v, entityClassType);
		this.baseMapper.insert(t);
	}
	
	@CacheDeleteTransactional
	@CacheDelete(@CacheDeleteKey(value="#target.getClass().getName() + '-' + #args[0].id"))
	@Override
	public void update(V v) {
		Assert.checkArgument(v);
		Wrapper<T> wrapper = primaryKeyCondition(v);
		Assert.CheckNotNull(wrapper,ExceptionEnum.INVALID_PK_FOR_UPDATE);
		T t = beanMapper.map(v, entityClassType);
		int num = this.baseMapper.update(t, wrapper);
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_UPDATE_NUM);
	}
	
	@CacheDeleteTransactional
	@CacheDelete(@CacheDeleteKey(value="#target.getClass().getName() + '-' + #args[0].id"))
	@Override
	public void delete(V v) {
		Assert.checkArgument(v);
		Wrapper<T> wrapper = primaryKeyCondition(v);
		Assert.CheckNotNull(wrapper,ExceptionEnum.INVALID_PK_FOR_DELETE);
		int num = this.baseMapper.delete(wrapper);
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_DELETE_NUM);
	}

}
