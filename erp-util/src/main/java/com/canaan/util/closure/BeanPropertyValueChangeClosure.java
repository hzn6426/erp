package com.canaan.util.closure;


import org.apache.commons.collections4.Closure;
import org.apache.commons.lang3.Validate;

import com.canaan.util.tool.BeanUtil;

public class BeanPropertyValueChangeClosure<T> implements Closure<T> {

	private final String propertyName;
	
	private final Object propertyValue;
	
	public BeanPropertyValueChangeClosure(String propertyName, Object propertyValue){
        Validate.notBlank(propertyName, "propertyName can't be blank!");
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }
	
	@Override
	public void execute(T input) {
		if (null == input) {
			return;
		}
		BeanUtil.setProperty(input, propertyName, propertyValue);
		
	}

}
