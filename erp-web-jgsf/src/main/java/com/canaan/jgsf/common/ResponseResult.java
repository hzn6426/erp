package com.canaan.jgsf.common;


import java.util.List;

import com.alibaba.fastjson.JSON;
import com.canaan.jgsf.exception.ClientBizException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
/**
 * Ajax请求返回结果，所有的Ajax请求都返回该对象的反序列化串，包括发生异常，
 * <tt>code</tt> 与 <tt>message</tt> 对应请参考<b> Exception_zh_CN.properties</b>
 * @author zening
 * @date 2017年12月21日 上午9:10:32
 * @version V1.0
 */
@JsonInclude(Include.NON_NULL)
@Getter 
@ApiModel("Ajax返回结果")
public class ResponseResult<E> {
	@ApiModelProperty("状态码")
	private int code;
	@ApiModelProperty("消息")
	private String message;
	@ApiModelProperty("返回数据")
	private List<E> data;
	@ApiModelProperty("数据总条数")
	private Integer totalSize;
	
	private ResponseResult(int code, String message, int totalSize, List<E> data) {
		this.code = code;
		this.message = message;
		this.totalSize = totalSize;
		this.data = data;
	}
	
	public static <E> ResponseResult<E> build() {
		return new ResponseResult<E>(200, "OK", 0, Lists.newArrayList());
	}
	
	public static <E> ResponseResult<E> build(int code, String message) {
		return new ResponseResult<E>(code, message, 0, Lists.newArrayList());
	}
	
	public static <E> ResponseResult<E> build(List<E> data) {
		return new ResponseResult<E>(200, "OK", data == null ? 0 : data.size(), data);
	}
	
	public static <E> ResponseResult<E> build(int totalSize, List<E> data) {
		return new ResponseResult<E>(200, "OK",totalSize, data);
	}
	
	public static <E> ResponseResult<E> build(int code, String message, int totalSize, List<E> data) {
		return new ResponseResult<E>(code, message,totalSize, data);
	}
	
	public static <E> ResponseResult<E> build(ClientBizException bizException) {
		return bizException == null ? build() : build(bizException.getCode(), bizException.getMessage());
		
	}
	
	public  String json() {
		return JSON.toJSONString(this);
	}
	
}
