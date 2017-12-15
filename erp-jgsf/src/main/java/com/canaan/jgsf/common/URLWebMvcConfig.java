package com.canaan.jgsf.common;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class URLWebMvcConfig extends WebMvcConfigurationSupport{
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		return new RequestMappingHandlerMapping() {
	    	private final static String API_BASE_PATH = "api";
	
	    	@Override
	        protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
	        	Class<?> beanType = method.getDeclaringClass();
	        	RestController restApiController = beanType.getAnnotation(RestController.class);
	        	if (restApiController != null) {
	            	PatternsRequestCondition apiPattern = new PatternsRequestCondition(API_BASE_PATH)
	                	.combine(mapping.getPatternsCondition());
	            	mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
	                	mapping.getMethodsCondition(), mapping.getParamsCondition(),
	                	mapping.getHeadersCondition(), mapping.getConsumesCondition(),
	                    	mapping.getProducesCondition(), mapping.getCustomCondition());
	             }
	             super.registerHandlerMethod(handler, method, mapping);
	    	}
		};
	}
}
