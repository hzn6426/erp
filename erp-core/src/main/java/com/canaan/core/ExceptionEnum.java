package com.canaan.core;

import lombok.Getter;

public enum ExceptionEnum {

	UN_CHECKED_EXCEPTION(10000),
	NO_BUNDLE_CODE(10001),
	OBJECT_IS_NULL(10002),
	ILLEGAL_ARGUMENT(10003),
	INVALID_ID_FOR_UPDATE(1004),
	INVALID_ID_FOR_INSERT(1005),
	INVALID_ID_FOR_DELETE(1006),
	INVALID_UPDATE_NUM(1007),
	INVALID_DELETE_NUM(1008),
	INVALID_PK_FOR_UPDATE(1009),
	INVALID_PK_FOR_DELETE(1010);
	
	
	@Getter
	private final int code;
	
	ExceptionEnum(final int code){
		this.code = code;
	}
}
