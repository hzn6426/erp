package com.canaan.util.tool;



import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.Validate;


/**
 * bean工具类，提供设置 获取属性等相关的简洁方法
 * @author zening
 * @date 2018年1月30日 上午10:08:28
 * @since 1.0.0
 */
public class BeanUtil {
	
	private BeanUtil() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}
	
	/**
	 * 获取对象的属性名称，以属性名<code>propertyNames</code>列表为key,具体值为value，返回map，如果propertyNames不指定则默认为对象所有属性。
	 * @param bean 操作的对象
	 * @param propertyNames 属性名称
	 * @return 属性名和值对应的map
	 */
	public static Map<String, Object> describe(Object bean, String...propertyNames) {
        Validate.notNull(bean, "bean can't be null!");
        if (Checker.BeNotEmpty(propertyNames)){
            try{
                return PropertyUtils.describe(bean);
            }catch (Exception e){
                throw new RuntimeException("describe exception", e);
            }
        }
        Map<String, Object> map = new LinkedHashMap<>(propertyNames.length);
        for (String propertyName : propertyNames){
            map.put(propertyName, getProperty(bean, propertyName));
        }
        return map;
    }
	
	/**
	 * 获取对象的属性<code>propertyName</code>对应的值
	 * @param <T> 对象属性值对应的类型
	 * @param bean 操作的对象
	 * @param propertyName 属性名称
	 * @return 获取的对象值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getProperty(Object bean, String propertyName) {
        Validate.notNull(bean, "bean can't be null!");
        Validate.notBlank(propertyName, "propertyName can't be blank!");
        try {
            return (T) PropertyUtils.getProperty(bean, propertyName);
        } catch (Exception e) {
            throw new RuntimeException("getProperty exception", e);
        }
    }
	
	/**
	 * 设置对象对应的属性<code>propertyName</code>名称对应的值为<code>value</code>，操作当value不为null有效.
	 * @param bean 操作的对象
	 * @param propertyName 属性名称
	 * @param value 设置的属性值
	 */
	public static void setPropertyIfValueNotNull(Object bean, String propertyName, Object value) {
        if (null != value) {
            setProperty(bean, propertyName, value);
        }
    }
	
	/**
	 * 设置对象对应的属性<code>propertyName</code>名称对应的值为<code>value</code>.
	 * @param bean 操作的对象
	 * @param propertyName 属性名称
	 * @param value 设置的属性值
	 */
	public static void setProperty(Object bean, String propertyName, Object value) {
        Validate.notNull(bean, "bean can't be null!");
        Validate.notBlank(propertyName, "propertyName can't be null!");
        try {
            PropertyUtils.setProperty(bean, propertyName, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	
}
