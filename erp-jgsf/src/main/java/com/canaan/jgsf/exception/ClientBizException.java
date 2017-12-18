package com.canaan.jgsf.exception;


import java.text.MessageFormat;
import java.util.ResourceBundle;

import lombok.Getter;

/**
 * PC端异常
 * @author Frog
 *
 */
@SuppressWarnings("serial")
public class ClientBizException extends RuntimeException {
	private final static ResourceBundle bundle = ResourceBundle.getBundle("Exception");
	
	@Getter
	private int code;
	
	@Getter
	private Object[] arguments;
	
	public ClientBizException(ClientExceptionEnum clientExceptionEnum) {
		super(getBundleMessage(clientExceptionEnum.getCode()));
		this.code = clientExceptionEnum.getCode();
	}
	
	public ClientBizException(ClientExceptionEnum clientExceptionEnum, Object... arguments) {
		super(MessageFormat.format(getBundleMessage(clientExceptionEnum.getCode()), arguments));
		this.code = clientExceptionEnum.getCode();
		this.arguments = arguments;
	}
	
	private static String getBundleMessage(int code) {
		String message =  bundle.getString(String.valueOf(code));
		if (message == null) {
			message = getBundleMessage(ClientExceptionEnum.NO_BUNDLE_CODE.getCode());
			if (message == null) {
				message = "NO BUNDLE CODE:" + ClientExceptionEnum.NO_BUNDLE_CODE.getCode();
			} else {
				message = MessageFormat.format(message,code);
			}
		}
		return message;
	}
	
	
}
