package com.canaan.core.exception;

import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import com.alibaba.dubbo.rpc.RpcContext;
import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.distribute.util.SnowflakeIdWorker;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局异常监控，AOP方式监控方法的执行进行异常捕获及处理
 * @author Frog
 * @since 2017年11月16日
 */
@Aspect
@Order(1000)
@Slf4j
public class ExceptionAspect {

	private ProtocolExceptionHandler handler = ProtocolExceptionHandler.getInstance();
	
	@Around("execution(* com.canaan..service.*.*(..))")
	public Object catchException(ProceedingJoinPoint point) throws Throwable {
		Object r = null;
		try {
			r = point.proceed(); 
		} catch (ServerException | DistributeException | BizException bizex) {
//			log.error(Throwables.getStackTraceAsString(bizex));
			handler.doProtocolException(bizex);
		} catch (IllegalArgumentException illAex) {
//			log.error(Throwables.getStackTraceAsString(illAex));
			String message = getMessageByException(illAex);
			handler.doProtocolException(new ServerException(ExceptionEnum.ILLEGAL_ARGUMENT,message));
		} catch (NullPointerException npex) {
//			log.error(Throwables.getStackTraceAsString(npex));
			String message = getMessageByException(npex);
			handler.doProtocolException(new ServerException(ExceptionEnum.OBJECT_IS_NULL,message));
		} catch (Exception ex) {
//			log.error(Throwables.getStackTraceAsString(ex));
			String message = getMessageByException(ex);
			handler.doProtocolException(new ServerException(ExceptionEnum.UN_CHECKED_EXCEPTION,message));
		}
		return r;
	}
	
	private String getMessageByException(Throwable throwable) {
		String name = RpcContext.getContext().getUrl().getParameters().get("application");
		name = Optional.ofNullable(name).orElse("");
		String uuid = SnowflakeIdWorker.getId();
		log.error("ServerException (" + uuid + "):" + Throwables.getStackTraceAsString(throwable));
		String message = Optional.ofNullable(throwable.getMessage()).orElse("");
//		return message += "(" + name + "<" + uuid + ">)";
		return message;
	}
	
	
}
