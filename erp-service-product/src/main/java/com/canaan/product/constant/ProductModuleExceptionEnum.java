package com.canaan.product.constant;

import com.canaan.core.exception.ExceptionEnumable;

public enum ProductModuleExceptionEnum implements ExceptionEnumable {

	EXCEPTION_SAMPLE(10020);
	
	ProductModuleExceptionEnum(final int code) {
		this.code = code;
	}
	
	private int code;
	
	@Override
	public int getExceptionCode() {
		return this.code;
	}

	
}
