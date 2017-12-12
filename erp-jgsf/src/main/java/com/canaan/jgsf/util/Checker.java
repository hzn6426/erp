package com.canaan.jgsf.util;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

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
}
