package com.canaan.jgsf.exception;

import lombok.Getter;

public enum ClientExceptionEnum {
	UN_CHECKED_EXCEPTION(1000),
	NO_BUNDLE_CODE(1001),
	OBJECT_IS_NULL(1002),
	ILLEGAL_ARGUMENT(1003),
	DATA_IS_NOT_VALID(1004),
	DISTRIBUTE_EXCEPTION(1005),
	DISABLE_ACCOUNT_EXCEPTION(1006),
	LOCKED_ACCOUNT_EXCEPTION(1007),
	INCORRECT_CREDENTIALS_EXCEPTION(1008),
	EXCESSIVE_ATTEMPTS_EXCEPTION(1009);
	
	@Getter
	private  int code;
	

	
	ClientExceptionEnum(final int code){
		this.code = code;
	}
}
