package com.canaan.core.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SelectField;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.jooq.impl.DSL;

import com.canaan.common.SearchResult;
import com.canaan.core.exception.ExceptionEnum;
import com.canaan.core.exception.ServerException;
import com.canaan.core.util.Assert;
import com.canaan.distribute.util.Checker;
import com.canaan.common.BaseModel;
import com.canaan.common.BaseService;
@SuppressWarnings({"unchecked" })
public abstract class BaseServiceImpl<R extends TableRecord<R>, T extends Table<R>, E extends BaseModel> 
	implements BaseService<E> {

	private  Class<R> recordClassType;
	
	private Class<E> modelClassType;
	
	public  BaseServiceImpl() {
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        recordClassType = (Class<R>) params[0];
        modelClassType = (Class<E>) params[2];
	}
	
	
	public abstract Condition condition(E e);
	
	public abstract List<SortField<?>> orderby(E e);
	
	public abstract Condition primaryKeyCondition(E e);
	
	public abstract List<SelectField<?>> select();
	
	

	
	@Resource
	protected DSLContext dsl;
	
	@Resource
	protected Mapper baseMapper;
	
	
	@Override
	public SearchResult<E> list(E e, int pageNumber, int pageSize) {
		int offset =  (pageNumber-1) * pageSize; 
		Assert.CheckArgument(false, e);
		R record = baseMapper.map(e, recordClassType);
		Condition conditions = condition(e);
		List<SortField<?>> orderbys = orderby(e);
		List<SelectField<?>> selecets = select();
		if (!Checker.BeNotNull(conditions)) {
			conditions = DSL.trueCondition();
		}
		if (!Checker.BeNotNull(orderbys)) {
			orderbys = new ArrayList<SortField<?>>(0);
		}
		if (!Checker.BeNotNull(selecets)) {
			selecets = new ArrayList<>(Arrays.asList(record.getTable().fields()));;
		}
		List<E> dataList = dsl.select(selecets).from(record.getTable()).where(conditions).orderBy(orderbys).limit(pageSize).offset(offset).fetchInto(modelClassType);
		int count = dsl.selectCount().from(record.getTable()).where(conditions).fetchOne(0, Integer.class);
		SearchResult<E> result = new SearchResult<>(count,dataList);
		return result;
	}
	
	
	@Override
	public E get(E e) {
		Assert.checkArgument(e);
		Condition pkConditon = primaryKeyCondition(e);
		Assert.CheckNotNull(pkConditon, ExceptionEnum.INVALID_PK_FOR_GET);
		R record = baseMapper.map(e, recordClassType);
		E data = dsl.selectFrom(record.getTable()).where(pkConditon).fetchOneInto(modelClassType);
		return data;
	}


	@Override
	public void save(E e) {
		Assert.checkArgument(e);
//		if (null != e.getId()) {
//			throw new ServerException(ExceptionEnum.INVALID_ID_FOR_INSERT);
//		}
		//TODO user getid
		long misecond = new Date().getTime();
//		e.setCreateId(createId);
		e.setCreateTime(misecond);
//		e.setUpdateId(updateId);
		e.setUpdateTime(misecond);
		R record = baseMapper.map(e, recordClassType);
		dsl.insertInto(record.getTable()).set(record).execute();
	}
	
	public void update(E e) {
		Assert.checkArgument(e);
//		Assert.CheckNotNull(e.getId(), ExceptionEnum.INVALID_ID_FOR_UPDATE);
		Condition pkConditon = primaryKeyCondition(e);
		Assert.CheckNotNull(pkConditon, ExceptionEnum.INVALID_PK_FOR_UPDATE);
		long misecond = new Date().getTime();
		//TODO user getid
//		e.setUpdateId(updateId);
		e.setUpdateTime(misecond);
		R record = baseMapper.map(e, recordClassType);
		int num = dsl.update(record.getTable()).set(record).where(pkConditon).execute();
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_UPDATE_NUM);
	}
	
	public void delete(E e) {
		Assert.checkArgument(e);
//		Assert.CheckNotNull(e.getId(), ExceptionEnum.INVALID_ID_FOR_DELETE);
		Condition pkConditon = primaryKeyCondition(e);
		Assert.CheckNotNull(pkConditon, ExceptionEnum.INVALID_PK_FOR_DELETE);
		R record = baseMapper.map(e, recordClassType);
		int num = dsl.deleteFrom(record.getTable()).where(pkConditon).execute();
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_DELETE_NUM);
	}
}
