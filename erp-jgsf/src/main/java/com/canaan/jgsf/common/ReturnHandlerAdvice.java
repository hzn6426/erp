package com.canaan.jgsf.common;

import java.util.Collection;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.canaan.jgsf.constant.SystemConstants;
import com.canaan.jgsf.util.WebUtil;

/**
 * 封装统一返回结果
 * <ul>
 * 	<li>void类型会统一封装成{@link ResponseResult}</li>
 * 	<li>ModelAndView会原样返回</li>
 * 	<li>{@link ResponseResult}类型会进行判断是否抛出异常，如果抛出异常并且是<tt>Ajax</tt>请求，封装ModelAndView返回错误页面，否则原样返回</li>
 * 	<li> 其他类型封装成{@link ResponseResult}</li>
 * </ul>
 * @author zening
 * @date 2017年12月20日 上午9:28:34
 * @version V1.0
 */
//@RestControllerAdvice
public class ReturnHandlerAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		String name = returnType.getMethod().getDeclaringClass().getName();
		return name.startsWith("com.canaan");
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		//封装返回结果
		if (body == null) {
			return ResponseResult.build();
		}
		
		if (ModelAndView.class.isInstance(body)) {
			return (ModelAndView) body;
		}
		
		if (ResponseResult.class.isInstance(body)) {
			return (ResponseResult) body;
		}
		
		if (Collection.class.isInstance(body)) {
			Collection<?> ction = (Collection<?>) body;
			return ResponseResult.build(ction.size(), body);
		}
		
		return ResponseResult.build(0, body);
	}

}
