package com.canaan.distribute.util;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;


public class Checker {

	public static <T> boolean BeNotNull(T reference) {
		if (reference == null) {
			return false;
		}
		return true;
	}
	
	public static <T> boolean BeNotNull(Collection<T> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return false;
		}
		return true;
	}
	
	public static <T> Boolean BeNotEmpty(Collection<T> coll) {
		return CollectionUtils.isNotEmpty(coll);
	}
	
	public static Boolean BeNotEmpty(CharSequence cs) {
		return StringUtils.isNotEmpty(cs);
	}
	
	public static Boolean BeNotBlank(CharSequence cs) {
		return StringUtils.isNotBlank(cs);
	}
	
}
