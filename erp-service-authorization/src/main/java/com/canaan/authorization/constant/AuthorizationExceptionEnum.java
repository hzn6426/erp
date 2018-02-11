package com.canaan.authorization.constant;

import com.canaan.core.exception.ExceptionEnumable;

public enum AuthorizationExceptionEnum implements ExceptionEnumable {

	MENU_OF_BUTTON_REFERENCE_NOT_EXIST(10020),
	INVALID_NODE_ID_EXCEPTION(10021),
	INVALID_ROLE_ID_EXCEPTION(10022);
	AuthorizationExceptionEnum(final int code) {
		this.code = code;
	}
	private int code;

	@Override
	public int getExceptionCode() {
		return code;
	}

}
