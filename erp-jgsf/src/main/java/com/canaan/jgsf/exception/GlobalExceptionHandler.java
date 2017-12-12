package com.canaan.jgsf.exception;

import java.util.ResourceBundle;


import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.canaan.jgsf.common.ResponseResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private final static ResourceBundle bundle = ResourceBundle.getBundle("ClientException");

	@ExceptionHandler(value = ClientBizException.class)
	public ResponseResult defaultExceptionHandler(ClientBizException clientException) throws Exception {
		ResponseResult result = handleClientBizException(clientException);
		return result;
	}
	@ExceptionHandler(value=BindException.class)
	public ResponseResult validateExceptionHandler(BindException bindException) {
		ResponseResult result = handleBindException(bindException);
		return result;
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseResult exceptionHandler(Exception ex) {
		ResponseResult result = handleException(ex);
		return result;
	}
	/**
	 * Exception                                   HTTP Status Code
	  ConversionNotSupportedException             500 (Internal Server Error)
	  HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
	  HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
	  HttpMessageNotReadableException             400 (Bad Request)
	  HttpMessageNotWritableException             500 (Internal Server Error)
	  HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
	  MissingServletRequestParameterException     400 (Bad Request)
	  NoSuchRequestHandlingMethodException        404 (Not Found)
	  TypeMismatchException 					  400 (Bad Request)
	 * @param ex
	 * @return
	 */
	private ResponseResult handleException(Exception ex) {
		return null;
//		ex.printStackTrace();
//		ResponseResult result = new ResponseResult();
//		result.setMessage(ex.getMessage());
//		result.setStackMessage(Throwables.getStackTraceAsString(ex).substring(0, 200));
//		if (ex instanceof ConversionNotSupportedException || ex instanceof HttpMessageNotWritableException) {
//			result.setCode(-9529);
//			result.setMessage("系统繁忙，请稍后重试!");
//		} else if (ex instanceof HttpMediaTypeNotAcceptableException) {
//			result.setCode(-9529);
//			result.setMessage("不支持的媒体类型!");
//		} else if (ex instanceof HttpMediaTypeNotSupportedException) {
//			result.setCode(-9529);
//			result.setMessage("不支持的媒体类型!");
//		} else if (ex instanceof HttpMessageNotReadableException || ex instanceof TypeMismatchException) {
//			result.setCode(-9529);
//			result.setMessage("无法读取消息!");
//		} else if (ex instanceof HttpRequestMethodNotSupportedException) {
//			result.setCode(-9529);
//			result.setMessage("不支持该请求方法!");
//		} else if (ex instanceof MissingServletRequestParameterException) {
//			result.setCode(-9529);
//			result.setMessage("系统参数不完整!");
//		} else {
//			result.setCode(-9529);
//			result.setMessage("系统内部错误!");
//		}
//		return result;
	}
	private ResponseResult handleBindException(BindException bindException) {
//		bindException.printStackTrace();
//		ResponseResult<JSONObject> result = new ResponseResult<JSONObject>();
//		int code = 10000;
//		String message = getExceptionMsgByCode(String.valueOf(code));
//		result.setSuccess(false);
//		result.setValidator(true);
//		result.setMessage(message);
//		result.setStackMessage(Throwables.getStackTraceAsString(bindException).substring(0, 200));
//		result.setCode(code);
//		List<FieldError> fieldErrors=bindException.getBindingResult().getFieldErrors();
//		List<JSONObject> errorList = new ArrayList<JSONObject>();
//        for (FieldError error:fieldErrors){
//        	JSONObject json = new JSONObject();
//        	//error.getDefaultMessage is the code define in the ClientException_zh_CN.properties.
//        	//it should load 'locale' parameters in the session to realize the true i18n
//        	json.put(error.getField(), getExceptionMsgByCode(error.getDefaultMessage()));
//        	errorList.add(json);
//        }
//        result.setData(errorList);
//        //the return structure in data is [{errorFileName:the code of @valid message value in ClientException_zh_CN.properties},{...}].
//		return result;
		return null;
	}
	
	private ResponseResult handleClientBizException(ClientBizException ex) {
//		ex.printStackTrace();
//		int code = ex.getCode();
//		String message = getExceptionMsgByCode(String.valueOf(code));
//		if (ex.getArguments() != null && ex.getArguments().length > 0) {
//			message = MessageFormat.format(message, ex.getArguments());
//		}
//		if (StringUtils.isBlank(message)){
//			message = MessageFormat.format("无法找到编码为:{0}的异常信息",code);
//		}
//		ResponseResult<Object> result = new ResponseResult<Object>();
//		result.setSuccess(false);
//		result.setMessage(message);
//		result.setStackMessage(Throwables.getStackTraceAsString(ex).substring(0,200));
//		return result;
		return null;
	}
//	private  String getExceptionMsgByCode(String code) {
//		//the bundle should reload the i18n message when used locale difference with the session locale attribute.
//    	return bundle.getString(code) == null ? "" : bundle.getString(code);
//    }
}
