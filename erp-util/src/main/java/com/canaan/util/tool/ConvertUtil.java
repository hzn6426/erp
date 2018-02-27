package com.canaan.util.tool;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.Validate;


public class ConvertUtil {
	
	private ConvertUtil() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}
	
	/**
	 * 将数组转化成map
	 * @param <K> key的对象类型
	 * @param <V> value的对象类型
	 * @param array 需要转化的数组
	 * @return 转化的map
	 */
	public static <K, V> Map<K, V> toMap(Object[] array) {
		Map<K, V> map = new LinkedHashMap<>();
		map = MapUtils.putAll(map, array);
		return map;
	}
	
	/**
	 * 将数组转化为list
	 * @param <T>数组类型
	 * @param arrays 数组
	 * @return 转化的list列表
	 */
	@SafeVarargs
    public static <T> List<T> toList(T...arrays){
        return !Checker.BeNotEmpty(arrays) ? Collections.<T> emptyList() : new ArrayList<>(Arrays.asList(arrays));
    }
	
	/**
	 * 将set集合转化成list
	 * @param <T> 集合类型
	 * @param set 集合
	 * @return 转化的List列表
	 */
	public static <T> List<T> toList(Set<T> set) {
		return !Checker.BeNotEmpty(set) ? Collections.<T> emptyList() : new ArrayList<>(set);
	}
	
	/**
	 * 将数组转化为set
	 * @param <T>数组类型
	 * @param arrays 数组
	 * @return 转化的set列表
	 */
	@SafeVarargs
    public static <T> Set<T> toSet(T...arrays){
        return !Checker.BeNotEmpty(arrays) ? Collections.<T> emptySet() : new LinkedHashSet<>(Arrays.asList(arrays));
    }
	
	/**
	 * 将集合转化为<code>arrayComponentType</code>类型的数组
	 * @param <T>集合类型
	 * @param collection 集合
	 * @param arrayComponentType 数组类型
	 * @return
	 */
	public static <T> T[] toArray(Collection<T> collection,Class<T> arrayComponentType){
        if (null == collection){
            return null;
        }

        Validate.notNull(arrayComponentType, "arrayComponentType must not be null");

        // 如果采用大家常用的把a的length设为0,就需要反射API来创建一个大小为size的数组,而这对性能有一定的影响.
        // 所以最好的方式就是直接把a的length设为Collection的size从而避免调用反射API来达到一定的性能优化.
        @SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(arrayComponentType, collection.size());

        //注意,toArray(new Object[0]) 和 toArray() 在功能上是相同的. 
        return collection.toArray(array);
    }
	
	/**
	 * 将字符创对象转化成
	 * @param toBeConvertedValue
	 * @param targetType
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static <T> T[] toArray(String[] toBeConvertedValue,Class<T> targetType){
        //如果指定的类型 本身就是数组类型的class,那么返回的类型就是该数组类型,否则将基于指定类型构造数组.
        return null == toBeConvertedValue ? null : (T[]) ConvertUtils.convert(toBeConvertedValue, targetType);
    }
}
