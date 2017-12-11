package com.canaan.core;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import com.canaan.distribute.exception.DistributeException;
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

	@Around("execution(* com.canaan..*service.*.*(..))")
	public Object catchException(ProceedingJoinPoint point) throws Throwable {
		Object r = null;
		try {
			r = point.proceed(); 
		} catch (ServerException | DistributeException bizex) {
			throw bizex;
		} catch (IllegalArgumentException illAex) {
			log.error(Throwables.getStackTraceAsString(illAex));
			throw new ServerException(ExceptionEnum.ILLEGAL_ARGUMENT);
		} catch (NullPointerException npex) {
			log.error(Throwables.getStackTraceAsString(npex));
			throw new ServerException(ExceptionEnum.OBJECT_IS_NULL);
		} catch (Exception ex) {
			log.error(Throwables.getStackTraceAsString(ex));
			throw new ServerException(ExceptionEnum.UN_CHECKED_EXCEPTION);
		}
		return r;
	}
}
