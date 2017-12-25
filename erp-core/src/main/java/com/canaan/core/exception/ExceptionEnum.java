package com.canaan.core.exception;


public enum ExceptionEnum implements ExceptionEnumable{

	UN_CHECKED_EXCEPTION(10000),
	NO_BUNDLE_CODE(10001),
	OBJECT_IS_NULL(10002),
	ILLEGAL_ARGUMENT(10003),
	INVALID_ID_FOR_UPDATE(10004),
	INVALID_ID_FOR_INSERT(10005),
	INVALID_ID_FOR_DELETE(10006),
	INVALID_UPDATE_NUM(10007),
	INVALID_DELETE_NUM(10008),
	INVALID_PK_FOR_UPDATE(10009),
	INVALID_PK_FOR_DELETE(10010),
	INVALID_PK_FOR_GET(10011);
	
	
	
	private  int code;
	

	@Override
	public int getExceptionCode() {
		return this.code;
	}


	ExceptionEnum(final int code){
		this.code = code;
	}
}
