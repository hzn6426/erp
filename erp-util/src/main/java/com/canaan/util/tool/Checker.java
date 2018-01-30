package com.canaan.util.tool;


import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 用于检查判断工具类，该类的方法总是返回boolean类型
 * @author zening
 * @date 2018年1月30日 上午10:21:30
 * @since 1.0.0
 */
public class Checker {

	private Checker() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}
	
	/**
	 * 断言对象不为null
	 * @param reference 判断的对象
	 * @return 断言正确返回true,否则为false
	 */
	public static <T> boolean BeNotNull(T reference) {
		if (reference == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 断言集合不为null
	 * @param collection 判断的集合
	 * @return 断言正确返回true,否则为false
	 */
	public static <T> boolean BeNotNull(Iterable<T> collection) {
		if (IterableUtils.isEmpty(collection)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 断言集合不为null且不为空
	 * @param coll 判断的集合
	 * @return 断言正确返回true,否则为false
	 */
	public static <T> Boolean BeNotEmpty(Iterable<T> coll) {
		return !IterableUtils.isEmpty(coll);
	}
	
	/**
	 * 断言map不为null且不为空
	 * @param map 判断的map
	 * @return 断言正确返回true,否则为false
	 */
	public static <K, V> boolean BeNotEmpty(Map<K, V> map) {
		return MapUtils.isNotEmpty(map);
	}
	
	/**
	 * 断言数组不能为空
	 * @param arr 判断的数组
	 * @return 断言正确返回true,否则为false
	 */
	public static <T> Boolean BeNotEmpty(T[] arr) {
		return ArrayUtils.isNotEmpty(arr);
	}
	
	/**
	 * 断言字符串不为null，不为空
	 * @param cs 判断的字符串
	 * @return 断言正确返回true,否则为false
	 */
	public static Boolean BeNotEmpty(CharSequence cs) {
		return StringUtils.isNotEmpty(cs);
	}
	
	/**
	 * 断言字符串不为null，不为空，不是全空格 
	 * @param cs 判断的字符串
	 * @return 断言正确返回true,否则为false
	 */
	public static Boolean BeNotBlank(CharSequence cs) {
		return StringUtils.isNotBlank(cs);
	}
	
}
