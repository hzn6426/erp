package com.canaan.core.exception;

import java.text.MessageFormat;

import com.alibaba.dubbo.rpc.RpcContext;
import com.canaan.core.util.Assert;
import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.util.tool.SnowflakeIdWorker;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProtocolExceptionHandler {
	
	private static final ProtocolExceptionHandler protocolExceptionHandler = new ProtocolExceptionHandler();
	private ProtocolExceptionHandler(){}
	
	public static ProtocolExceptionHandler getInstance() {
		return protocolExceptionHandler;
	}
	
	
	public void doProtocolException (Exception ex) {
		Assert.CheckArgument(ex);
		String protocol = RpcContext.getContext().getUrl().getProtocol();
		Assert.CheckNotNull(protocol);
		
		switch (protocol.toLowerCase()) {
		case "dubbo":
			handleDubboProtocolException(ex);
			break;

		default:
			handleOtherProtocolException(protocol, ex);
			break;
		}
		
		
	}
	
	
	
	private void handleDubboProtocolException(Exception re) {
		if (DistributeException.class.isInstance(re)) {
			DistributeException ds = (DistributeException) re;
			log.error(ds.getMessage());
			throw ds;
		}
		if (BizException.class.isInstance(re)) {
			throw (BizException) re;
		}
		//application name
		String name = Optional.fromNullable(RpcContext.getContext().getUrl().getParameters().get("application")).or("");
		String message = Optional.fromNullable(re.getMessage()).or("");
		String uuid = SnowflakeIdWorker.getId();
		ServerException se = null;
		if (ServerException.class.isInstance(re)) {
			se = (ServerException) re;
		} else if (NullPointerException.class.isInstance(re)) {
			se = new ServerException(ExceptionEnum.OBJECT_IS_NULL, message);
		} else if (IllegalArgumentException.class.isInstance(re)) {
			se = new ServerException(ExceptionEnum.ILLEGAL_ARGUMENT, message);
		} else {
			se = new ServerException(ExceptionEnum.UN_CHECKED_EXCEPTION, message);
		}
		String stackMessage = Throwables.getStackTraceAsString(re);
		log.error("ServerException (" + uuid + "):" + stackMessage);
		throw new BizException(name + "(" + uuid + ")", se.getCode(), se.getMessage(), stackMessage);
	}
	
	private void handleOtherProtocolException(String protocol,Exception re) {
		throw new RuntimeException(MessageFormat.format("the protocol : {0} handler is not realize.", protocol), re);
	}
	
	
}
