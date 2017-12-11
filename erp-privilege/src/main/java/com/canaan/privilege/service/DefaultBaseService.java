package com.canaan.privilege.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableRecord;
import org.jooq.impl.DSL;

import com.canaan.common.SearchResult;
import com.canaan.core.Assert;
import com.canaan.core.Checker;
import com.canaan.core.ExceptionEnum;
import com.canaan.core.ServerException;
import com.canaan.common.BaseModel;
import com.canaan.common.BaseService;
@SuppressWarnings({"unchecked" })
public abstract class DefaultBaseService<R extends TableRecord<R>, T extends Table<R>, E extends BaseModel> 
	implements BaseService<E> {

	private  Class<R> recordClassType;
	
	private Class<E> modelClassType;
	
	public  DefaultBaseService() {
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        recordClassType = (Class<R>) params[0];
        modelClassType = (Class<E>) params[2];
	}
	
	
	public abstract Condition condition(E e);
	
	public abstract SortField<?> orderby(E e);
	
	public abstract Condition primaryKeyCondition(E e);

	
	@Resource
	private DSLContext dsl;
	
	@Resource
	private Mapper baseMapper;
	
	
	@Override
	public SearchResult<E> list(E e, int start, int limit) {
		Assert.CheckArgument(false, e);
		int offset =  (limit - 1) * start;
		R record = baseMapper.map(e, recordClassType);
		Condition conditions = condition(e);
		SortField<?> orderbys = orderby(e);
		if (!Checker.BeNotNull(conditions)) {
			conditions = DSL.trueCondition();
		}
		if (!Checker.BeNotNull(orderbys)) {
			orderbys = DSL.count().desc();
		}
		List<E> dataList = dsl.selectFrom(record.getTable()).where(conditions).orderBy(orderbys).limit(limit).offset(offset).fetchInto(modelClassType);
		int count = dsl.selectCount().where(conditions).fetchOne(0, Integer.class);
		SearchResult<E> result = new SearchResult<>(count,dataList);
		return result;
	}
	
	@Override
	public void save(E e) {
		Assert.checkArgument(e);
		if (null != e.getId()) {
			throw new ServerException(ExceptionEnum.INVALID_ID_FOR_INSERT);
		}
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
		Assert.CheckNotNull(e.getId(), ExceptionEnum.INVALID_ID_FOR_UPDATE);
		Condition ctn = primaryKeyCondition(e);
		Assert.CheckNotNull(ctn, ExceptionEnum.INVALID_PK_FOR_UPDATE);
		long misecond = new Date().getTime();
		//TODO user getid
//		e.setUpdateId(updateId);
		e.setUpdateTime(misecond);
		R record = baseMapper.map(e, recordClassType);
		int num = dsl.update(record.getTable()).set(record).where(primaryKeyCondition(e)).execute();
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_UPDATE_NUM);
	}
	
	public void delete(E e) {
		Assert.checkArgument(e);
		Assert.CheckNotNull(e.getId(), ExceptionEnum.INVALID_ID_FOR_DELETE);
		Condition ctn = primaryKeyCondition(e);
		Assert.CheckNotNull(ctn, ExceptionEnum.INVALID_PK_FOR_DELETE);
		R record = baseMapper.map(e, recordClassType);
		int num = dsl.deleteFrom(record.getTable()).where(primaryKeyCondition(e)).execute();
		Assert.checkNotEqual(num, 1, ExceptionEnum.INVALID_DELETE_NUM);
	}
}
