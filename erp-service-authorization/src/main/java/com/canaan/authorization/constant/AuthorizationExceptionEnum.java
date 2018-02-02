package com.canaan.authorization.constant;

import com.canaan.core.exception.ExceptionEnumable;

public enum AuthorizationExceptionEnum implements ExceptionEnumable {

	EXCEPTION_SAMPLE(10020);
	AuthorizationExceptionEnum(final int code) {
		this.code = code;
	}
	private int code;

	@Override
	public int getExceptionCode() {
		return code;
	}

}
