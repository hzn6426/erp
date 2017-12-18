package com.canaan.distribute.exception;

import lombok.Getter;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = 9018640004766233134L;
	@Getter
	private String uuid;
	@Getter
	private int code;
	public BizException(int code,String message) {
		super(message);
		this.code = code;
	}
}
