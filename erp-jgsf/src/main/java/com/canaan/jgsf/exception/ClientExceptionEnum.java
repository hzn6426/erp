package com.canaan.jgsf.exception;

import lombok.Getter;

public enum ClientExceptionEnum {
	UN_CHECKED_EXCEPTION(1000),
	NO_BUNDLE_CODE(1001),
	OBJECT_IS_NULL(1002),
	ILLEGAL_ARGUMENT(1003);
	
	@Getter
	private  int code;
	

	
	ClientExceptionEnum(final int code){
		this.code = code;
	}
}
