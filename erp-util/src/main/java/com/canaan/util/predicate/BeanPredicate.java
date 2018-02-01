package com.canaan.util.predicate;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.Validate;

import com.canaan.util.tool.BeanUtil;

/**
 * 用于对象属性值预言的对象
 * @author zening
 * @date 2018年2月1日 上午11:42:07
 * @since 1.0.0
 */
public class BeanPredicate<T> implements Predicate<T>{
	
	private final String propertyName;

    /** The value predicate. */
    @SuppressWarnings("rawtypes")
	private final Predicate valuePredicate;
    
    @SuppressWarnings("rawtypes")
	public BeanPredicate(String propertyName, Predicate valuePredicate){
        Validate.notBlank(propertyName, "propertyName can't be blank!");
        Validate.notNull(valuePredicate, "predicate can't be null!");
        this.propertyName = propertyName;
        this.valuePredicate = valuePredicate;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public boolean evaluate(T object){
        Object currentPropertyValue = BeanUtil.getProperty(object, propertyName);
        return valuePredicate.evaluate(currentPropertyValue);
    }
}
