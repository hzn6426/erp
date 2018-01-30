package com.canaan.util.predicate;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.Validate;

import com.canaan.util.tool.BeanUtil;


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
