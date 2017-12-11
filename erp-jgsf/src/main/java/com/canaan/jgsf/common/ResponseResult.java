package com.canaan.jgsf.common;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;

import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Getter 
public class ResponseResult {

	private int code;
	private String message;
	private List<?> data;
	private Integer totalSize;
	
	private ResponseResult(int code, String message, List<?> data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public static ResponseResult builder() {
		return new ResponseResult(200, "OK", Lists.newArrayList());
	}
	
	public static ResponseResult builder(List<?> data) {
		return new ResponseResult(200, "OK", data);
	}
	
	public static ResponseResult builder(int code, String message, List<?> data) {
		return new ResponseResult(code, message, data);
	}
	
	public  String json() {
		return JSON.toJSONString(this);
	}
	
}
