package com.canaan.privilege.common;

import com.canaan.core.exception.ExceptionEnumable;

public enum PrivilegeExceptionEnum implements ExceptionEnumable {

	EXCEPTION_SAMPLE(10020);
	
	PrivilegeExceptionEnum(final int code) {
		this.code = code;
	}
	
	private int code;
	
	@Override
	public int getExceptionCode() {
		return this.code;
	}

	
}
