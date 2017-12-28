package com.canaan.core.exception;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 全局异常监控，AOP方式监控方法的执行进行异常捕获及处理
 * <ol>
 * <li>ExceptionAspect: 全局异常监控，任何service包中的异常都会进行捕捉，根据协议封装成相应的exception</li>
 * <li>dubbo协议：内部协议，会封装为BizException，调用端获取后BizException后进行统一处理</li>
 * <li> Rest协议：此为以后进行扩展，对外支持接口协议，封装成通用数据格式。</li>
 * </ol>
 * @author Frog
 * @since 2017年11月16日
 */
@Component
@Aspect
@Order(1000)
public class ExceptionAspect {

	private ProtocolExceptionHandler handler = ProtocolExceptionHandler.getInstance();
	
	@Around("execution(* com.canaan..service..*.*(..))")
	public Object catchException(ProceedingJoinPoint point) throws Throwable {
		Object r = null;
		try {
			r = point.proceed(); 
//		} catch (ServerException | DistributeException | BizException bizex) {
////			log.error(Throwables.getStackTraceAsString(bizex));
//			handler.doProtocolException(bizex);
//		} catch (IllegalArgumentException illAex) {
////			log.error(Throwables.getStackTraceAsString(illAex));
//			String message = getMessageByException(illAex);
//			handler.doProtocolException(new ServerException(ExceptionEnum.ILLEGAL_ARGUMENT,message));
//		} catch (NullPointerException npex) {
////			log.error(Throwables.getStackTraceAsString(npex));
//			String message = getMessageByException(npex);
//			handler.doProtocolException(new ServerException(ExceptionEnum.OBJECT_IS_NULL,message));
		} catch (Exception ex) {
////			log.error(Throwables.getStackTraceAsString(ex));
//			String message = getMessageByException(ex);
//			handler.doProtocolException(new ServerException(ExceptionEnum.UN_CHECKED_EXCEPTION,message));
			handler.doProtocolException(ex);
		}
		return r;
	}
	
//	private String getMessageByException(Throwable throwable) {
//		String name = RpcContext.getContext().getUrl().getParameters().get("application");
//		name = Optional.ofNullable(name).orElse("");
//		String uuid = SnowflakeIdWorker.getId();
//		log.error("ServerException (" + uuid + "):" + Throwables.getStackTraceAsString(throwable));
//		String message = Optional.ofNullable(throwable.getMessage()).orElse("");
////		return message += "(" + name + "<" + uuid + ">)";
//		return message;
//	}
	
	
}
