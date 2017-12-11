package com.canaan.core.exception;

import java.text.MessageFormat;

import com.alibaba.dubbo.rpc.RpcContext;
import com.canaan.core.util.Assert;
import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;

public class ProtocolExceptionHandler {
	
	private static final ProtocolExceptionHandler protocolExceptionHandler = new ProtocolExceptionHandler();
	private ProtocolExceptionHandler(){}
	
	public static ProtocolExceptionHandler getInstance() {
		return protocolExceptionHandler;
	}
	
	public void doProtocolException (RuntimeException ex) {
		Assert.checkArgument(ex);
		String protocol = RpcContext.getContext().getUrl().getProtocol();
		Assert.checkNotNull(protocol);
		
		switch (protocol.toLowerCase()) {
		case "dubbo":
			handleDubboProtocolException(ex);
			break;

		default:
			handleOtherProtocolException(protocol, ex);
			break;
		}
		
		
	}
	
	private void handleDubboProtocolException(RuntimeException re) {
		if (ServerException.class.isInstance(re)) {
			ServerException se = (ServerException) re;
			throw new BizException(se.getCode(), se.getMessage());
		} else if (DistributeException.class.isInstance(re)) {
			throw (DistributeException)re;
		}
	}
	
	private void handleOtherProtocolException(String protocol,RuntimeException re) {
		throw new RuntimeException(MessageFormat.format("the protocol : {0} handler is not realize.", protocol), re);
	}
	
	
}
