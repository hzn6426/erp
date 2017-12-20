package com.canaan.jgsf.common;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Getter 
public class ResponseResult {

	private int code;
	private String message;
	private Object data;
	private Integer totalSize;
	
	private ResponseResult(int code, String message, int totalSize, Object data) {
		this.code = code;
		this.message = message;
		this.totalSize = totalSize;
		this.data = data;
	}
	
	public static ResponseResult build() {
		return new ResponseResult(200, "OK", 0, new Object());
	}
	
	public static ResponseResult build(int code, String message) {
		return new ResponseResult(code, message, 0, new Object());
	}
	
	public static ResponseResult build(int totalSize, Object data) {
		return new ResponseResult(200, "OK",totalSize, data);
	}
	
	public static ResponseResult build(int code, String message, int totalSize, Object data) {
		return new ResponseResult(code, message,totalSize, data);
	}
	
	public  String json() {
		return JSON.toJSONString(this);
	}
	
}
