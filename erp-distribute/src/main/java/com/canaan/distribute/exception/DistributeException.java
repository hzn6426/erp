package com.canaan.distribute.exception;

/**
 * 自定义分布式异常 
 * @author frog
 * @date 2017年9月4日 上午9:06:40
 * @version V1.0
 */
public class DistributeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DistributeException(Throwable cause,String message) {
		super(message, cause);
	}
	
	public DistributeException(Throwable cause) {
		super(cause);
	}
}
