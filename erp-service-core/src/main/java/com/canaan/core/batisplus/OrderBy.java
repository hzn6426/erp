package com.canaan.core.batisplus;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.collect.Lists;

import lombok.Getter;

public class OrderBy {
	
	@Getter
	private final List<String> ascList = Lists.newArrayList(); 
	
	@Getter
	private final List<String> descList = Lists.newArrayList();
	
	public  OrderBy asc(String... fields) {
		if (ArrayUtils.isNotEmpty(fields)) {
			ascList.addAll(Lists.newArrayList(fields));
		}
		return this;
	}
	
	public OrderBy desc(String... fields) {
		if (ArrayUtils.isNotEmpty(fields)) {
			descList.addAll(Lists.newArrayList(fields));
		}
		return this;
	}
	
	
	
}
