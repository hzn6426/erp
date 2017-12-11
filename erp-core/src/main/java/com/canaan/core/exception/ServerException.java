package com.canaan.core.exception;

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
	
	public ServerException(ExceptionEnumable exceptionEnum) {
		super(getBundleMessage(exceptionEnum.getExceptionCode()));
		this.code = exceptionEnum.getExceptionCode();
	}
	
	public ServerException(ExceptionEnum exceptionEnum, Object... arguments) {
		super(MessageFormat.format(getBundleMessage(exceptionEnum.getExceptionCode()), arguments));
		this.code = exceptionEnum.getExceptionCode();
	}
	
	private static String getBundleMessage(int code) {
		String message =  bundle.getString(String.valueOf(code));
		if (message == null) {
			message = getBundleMessage(ExceptionEnum.NO_BUNDLE_CODE.getExceptionCode());
		}
		return message;
	}
}
