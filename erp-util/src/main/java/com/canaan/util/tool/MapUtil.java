package com.canaan.util.tool;

import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.Validate;

/**
 * map相关的工具类提供map的简洁操作
 * @author zening
 * @date 2018年1月30日 上午10:06:14
 * @since 1.0.0
 */
public class MapUtil {
	
	
	private MapUtil() {
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}
	
	/**
	 * 根据索引获取map的 entry
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param index 索引
	 * @return 索引对应的entry
	 */
	public static <K, V> Map.Entry<K, V> get(Map<K, V> map, int index){
        Validate.notNull(map, "map can't be null!");

        Set<Entry<K, V>> entrySet = map.entrySet();
        return IterableUtils.get(entrySet, index);
    }
	
	/**
	 * 仅当value不为null时，设置map的健和值
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param key 对应的键
	 * @param value 对应的值
	 */
	public static <K, V> void putIfValueNotNull(final Map<K, V> map, final K key, final V value){
        if (null != map && null != value) {
            map.put(key, value);
        }
    }
	
	/**
	 * 复制<code>m</code>中的健和值都不为null的数据到<code>map</code>
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param m 被复制的map
	 */
	public static <K, V> void putAllIfNotNull(final Map<K, V> map, Map<? extends K, ? extends V> m){
        if (null != map && null != m){
            map.putAll(m);// m 如果是null 会报错
        }
    }
	
	/**
	 * 设置map的key和value，如果key相同，value以list的方式存储
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param key 对应的键
	 * @param value 对应的值
	 * @return 操作后的map
	 */
	public static <K, V> Map<K, List<V>> putMultiValue(Map<K, List<V>> map, K key, V value) {
        Validate.notNull(map, "map can't be null!");

        List<V> list = defaultIfNull(map.get(key), new ArrayList<V>());
        list.add(value);

        map.put(key, list);
        return map;
    }
	
	/**
	 * 获取key列表匹配的健和值对应的子map
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param keys 对应的键列表
	 * @return 帅选后的map
	 */
	@SafeVarargs
	public static <K, V> Map<K, V> getSubMap(Map<K, V> map, K...keys) {
        if (!Checker.BeNotNull(keys)) {
            return map;
        }
        return getSubMap(map, new LinkedHashSet<>(Arrays.asList(keys)));
    }
	
	/**
	 * 获取key列表匹配的健和值对应的子map
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param keys 对应的键列表
	 * @return 帅选后的map
	 */
	public static <K, V> Map<K, V> getSubMap(Map<K, V> map, Iterable<K> keys) {
		if (!Checker.BeNotEmpty(map)) {
            return emptyMap();
        }
        if (!Checker.BeNotEmpty(keys)) {
            return map;
        }

        //保证元素的顺序,key的顺序 按照参数 <code>keys</code>的顺序
        Map<K, V> returnMap = new LinkedHashMap<>(10);
        for (K key : keys) {
            if (map.containsKey(key)) {
                returnMap.put(key, map.get(key));
            }
        }
        return returnMap;
    }
	
	/**
	 * 获取key列表<span style="color:red">不</span>匹配的健和值对应的子map
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param keys 对应的键列表
	 * @return 帅选后的map
	 */
	@SafeVarargs
	public static <K, V> Map<K, V> getSubMapExcludeKeys(Map<K, V> map, K...excludeKeys) {
        if (!Checker.BeNotEmpty(excludeKeys)) {
            return map;
        }

        return getSubMapExcludeKeys(map, new LinkedHashSet<>(Arrays.asList(excludeKeys)));
	}

	/**
	 * 获取key列表<span style="color:red">不</span>匹配的健和值对应的子map
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param keys 对应的键列表
	 * @return 帅选后的map
	 */
	public static <K, V> Map<K, V> getSubMapExcludeKeys(Map<K, V> map, Iterable<K> excludeKeys) {
	    if (!Checker.BeNotEmpty(map)) {
	        return emptyMap();
	    }
	    
	    if (!Checker.BeNotEmpty(excludeKeys)) {
	        return map;
	    }
	
	    Map<K, V> returnMap = new LinkedHashMap<>(map.size());//保证元素的顺序 
	
	    for (Map.Entry<K, V> entry : map.entrySet()) {
	        K key = entry.getKey();
	        if (!IterableUtils.contains(excludeKeys, key)) {
	            returnMap.put(key, entry.getValue());
	        }
	    }
	    return returnMap;
	}
	
	/**
	 * 删除键<code>keys</code>列表对应数据
	 * @param <K> Key的对象类型
	 * @param <V> Value的对象类型
	 * @param map 操作的map
	 * @param keys 需要删除的键列表
	 * @return 删除后的map
	 */
	@SafeVarargs
    public static <K, V> Map<K, V> removeKeys(Map<K, V> map, K...keys) {
        if (null == map) {
            return null;
        }

        if (!Checker.BeNotEmpty(keys)) {
            return map;
        }
        for (K key : keys) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }
        return map;
    }
	
	/**
	 * 以参数 <code>map</code>的key为key,以参数 <code>map</code> value的指定<code>extractPropertyName</code>属性值为值,拼装成新的map返回.
	 * <pre class="code">
     * Map{@code <Long, User>} map = new LinkedHashMap{@code <>}();
     * map.put(1L, new User(100L));
     * map.put(2L, new User(200L));
     * map.put(5L, new User(500L));
     * map.put(4L, new User(400L));
     * 
     * LOGGER.debug(JsonUtil.format(MapUtil.extractSubMap(map, "id")));
     * </pre>
     * 
     * <b>返回:</b>
     * 
     * <pre class="code">
	 *   {
	 *       "1": 100,
	 *       "2": 200,
	 *       "5": 500,
	 *       "4": 400
	 *   }
     * </pre>
	 * 
	 * @param <K> Key的对象类型
	 * @param <O> Value的对象类型
	 * @param <V> map value 对应对象相关 属性名称 <code>extractPropertyName</code> 的值类型
	 * @param map 操作的map
	 * @param extractPropertyName 属性名
	 * @return 操作后的map
	 */
	public static <K, O, V> Map<K, V> extractSubMap(Map<K, O> map, String extractPropertyName) {
        return extractSubMap(map, null, extractPropertyName);
    }
	
	/**
	 * 以参数 <code>map</code>的key经过<code>includeKeys</code>筛选后为key,以参数 <code>map</code>，
	 * value的指定<code>extractPropertyName</code>属性值为值,拼装成新的map返回.
	 * @param <K> Key的对象类型
	 * @param <O> Value的对象类型
	 * @param <V> map value 对应对象相关 属性名称 <code>extractPropertyName</code> 的值类型
	 * @param map  操作的map
	 * @param includeKeys
	 * @param extractPropertyName  属性名
	 * @return
	 */
	public static <K, O, V> Map<K, V> extractSubMap(Map<K, O> map, K[] includeKeys, String extractPropertyName) {
        if (!Checker.BeNotEmpty(map)) {
            return emptyMap();
        }

        Validate.notBlank(extractPropertyName, "extractPropertyName can't be null/empty!");

        //如果excludeKeys是null,那么抽取所有的key
        @SuppressWarnings("unchecked") // NOPMD - false positive for generics
        K[] useIncludeKeys = !Checker.BeNotEmpty(includeKeys) ? (K[]) map.keySet().toArray() : includeKeys;

        //保证元素的顺序,顺序是参数  includeKeys的顺序
        Map<K, V> returnMap = new LinkedHashMap<>(useIncludeKeys.length);
        for (K key : useIncludeKeys) {
            if (map.containsKey(key)) {
                returnMap.put(key, BeanUtil.<V> getProperty(map.get(key), extractPropertyName));
            }
        }
        return returnMap;
    }
}
