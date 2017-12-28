package com.canaan.distribute.util;

public class BeanUtil {
	/**
	 * 判断是否是原始类型（java 内置类型）
	 * @param key
	 * @return
	 */
	public static <T> boolean bePrimitive(T key) {
    	if (String.class.equals(key.getClass()) || Boolean.class.equals(key.getClass()) || key.getClass().isPrimitive()) {
    		return true;
    	}
    	return false;
    }
}
