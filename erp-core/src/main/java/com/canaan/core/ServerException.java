package com.canaan.core;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import lombok.Getter;
/**
 * 系统业务异常
 * @author Frog
 *
 */
public class ServerException extends RuntimeException {
	private static final long serialVersionUID = -5464600873374326848L;
	private final static ResourceBundle bundle = ResourceBundle.getBundle("Exception");
	
	@Getter
	private int code;
	
	public ServerException(ExceptionEnum exceptionEnum) {
		super(getBundleMessage(exceptionEnum.getCode()));
		this.code = exceptionEnum.getCode();
	}
	
	public ServerException(ExceptionEnum exceptionEnum, Object... arguments) {
		super(MessageFormat.format(getBundleMessage(exceptionEnum.getCode()), arguments));
		this.code = exceptionEnum.getCode();
	}
	
	private static String getBundleMessage(int code) {
		String message =  bundle.getString(String.valueOf(code));
		if (message == null) {
			message = getBundleMessage(ExceptionEnum.NO_BUNDLE_CODE.getCode());
		}
		return message;
	}
}
