package com.canaan.distribute.exception;

import lombok.Getter;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = 9018640004766233134L;
	@Getter
	private String uuid;
	@Getter
	private int code;
	@Getter
	private String stackMessage;
	public BizException(String uuid, int code, String message, String stackMessage) {
		super(message);
		this.code = code;
		this.uuid = uuid;
		this.stackMessage = stackMessage;
	}
}
