package com.canaan.privilege.common;

import com.canaan.core.exception.ExceptionEnumable;

public enum PrivilegeExceptionEnum implements ExceptionEnumable {

	EXCEPTION_SAMPLE(1000);
	
	PrivilegeExceptionEnum(final int code) {
		this.code = code;
	}
	
	private int code;
	
	@Override
	public int getExceptionCode() {
		return this.code;
	}

	
}
