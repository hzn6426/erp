package com.canaan.util.tool;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

public class ConvertUtil {
	
	private ConvertUtil() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}
	
	/**
	 * 将数组转化成map
	 * @param array 需要转化的数组
	 * @return 转化的map
	 */
	public static <K, V> Map<K, V> toMap(Object[] array) {
		Map<K, V> map = new LinkedHashMap<>();
		map = MapUtils.putAll(map, array);
		return map;
	}
	
}
