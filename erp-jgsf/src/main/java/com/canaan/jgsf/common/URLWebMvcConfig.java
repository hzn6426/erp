package com.canaan.jgsf.common;


import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//@Configuration
public class URLWebMvcConfig extends WebMvcConfigurationSupport{
//	@Bean
//	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//		return new RequestMappingHandlerMapping() {
//	    	private final static String API_BASE_PATH = "api";
//	
//	    	@Override
//	        protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
//	        	Class<?> beanType = method.getDeclaringClass();
//	        	RestApiController restApiController = beanType.getAnnotation(RestApiController.class);
//	        	if (restApiController != null) {
//	            	PatternsRequestCondition apiPattern = new PatternsRequestCondition(API_BASE_PATH)
//	                	.combine(mapping.getPatternsCondition());
//	            	mapping = new RequestMappingInfo(mapping.getName(), apiPattern,
//	                	mapping.getMethodsCondition(), mapping.getParamsCondition(),
//	                	mapping.getHeadersCondition(), mapping.getConsumesCondition(),
//	                    	mapping.getProducesCondition(), mapping.getCustomCondition());
//	             }
//	             super.registerHandlerMethod(handler, method, mapping);
//	    	}
//		};
//	}
}
