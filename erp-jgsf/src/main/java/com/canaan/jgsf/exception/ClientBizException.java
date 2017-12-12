package com.canaan.jgsf.exception;


import lombok.Getter;

/**
 * PC端异常
 * @author Frog
 *
 */
@SuppressWarnings("serial")
public class ClientBizException extends RuntimeException {
//	private final static ResourceBundle bundle = ResourceBundle.getBundle("Exception");
	
	@Getter
	private int code;
	
	@Getter
	private Object[] arguments;
	
	public ClientBizException(ClientExceptionEnum exceptionEnum) {
//		super(getBundleMessage(exceptionEnum.getCode()));
		this.code = exceptionEnum.getCode();
	}
	
	public ClientBizException(ClientExceptionEnum exceptionEnum, Object... arguments) {
//		super(MessageFormat.format(getBundleMessage(exceptionEnum.getCode()), arguments));
		this.code = exceptionEnum.getCode();
		this.arguments = arguments;
	}
	
//	private static String getBundleMessage(int code) {
//		String message =  bundle.getString(String.valueOf(code));
//		if (message == null) {
//			message = getBundleMessage(ClientExceptionEnum.NO_BUNDLE_CODE.getCode());
//		}
//		return message;
//	}
	
	
}
