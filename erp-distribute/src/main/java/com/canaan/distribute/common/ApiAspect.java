package com.canaan.distribute.common;


import org.aspectj.lang.ProceedingJoinPoint;


public class ApiAspect {
//	private static final Logger logger = LoggerFactory.getLogger(ApiAspect.class);  
	
	
	public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
		Object r = null;
		try {
			r =  point.proceed();
		} catch (Exception e) {
			throw e;
//			if (s == null) throw e;
//			
//			s.setThrowabling(Throwables.getStackTraceAsString(e).substring(0, 100));
			//封装自定义异常
		}
		return r;
	}
}
