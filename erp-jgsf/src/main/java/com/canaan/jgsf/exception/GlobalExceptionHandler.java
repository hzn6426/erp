package com.canaan.jgsf.exception;

import java.util.Iterator;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.jgsf.common.ResponseResult;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = ClientBizException.class)
	public ResponseResult defaultExceptionHandler(ClientBizException clientException) throws Exception {
		log.error(Throwables.getStackTraceAsString(clientException));
		ResponseResult result = handleClientBizException(clientException);
		return result;
	}
	@ExceptionHandler(value = {BindException.class ,MethodArgumentNotValidException.class})
	public ResponseResult validateExceptionHandler(BindException bindException) {
		log.error(Throwables.getStackTraceAsString(bindException));
		ResponseResult result = handleBindException(bindException);
		return result;
	}
	@ExceptionHandler(value = DistributeException.class)
	public ResponseResult DistributeExceptionHandler(DistributeException distributeException) {
		log.error(Throwables.getStackTraceAsString(distributeException));
		ResponseResult result =  handleDistributeException(distributeException);
		return result;
	}
	@ExceptionHandler(value = BizException.class)
	public ResponseResult BizExceptionHandler(BizException bizException) {
		log.error(Throwables.getStackTraceAsString(bizException));
		ResponseResult result =  handleBizException(bizException);
		return result;
	}
	@ExceptionHandler(value = {NullPointerException.class, IllegalArgumentException.class})
	public ResponseResult CommonRunTimeExceptionHandler(RuntimeException rex) {
		if (NullPointerException.class.isInstance(rex)) {
			String message = rex.getMessage() == null ? "" : rex.getMessage();
			throw new ClientBizException(ClientExceptionEnum.OBJECT_IS_NULL,message);
		} else if (IllegalArgumentException.class.isInstance(rex)) {
			String message = rex.getMessage() == null ? "" : rex.getMessage();
			throw new ClientBizException(ClientExceptionEnum.ILLEGAL_ARGUMENT,message);
		}
		return exceptionHandler(rex);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseResult exceptionHandler(Exception ex) {
		log.error(Throwables.getStackTraceAsString(ex));
		ResponseResult result = handleException(ex);
		return result;
	}
	
	private ResponseResult handleBizException(BizException bizException) {
		return ResponseResult.builder(bizException.getCode(), bizException.getMessage());
	}
	
	private ResponseResult handleDistributeException(DistributeException distributeException) {
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.DISTRIBUTE_EXCEPTION);
		return ResponseResult.builder(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult handleException(Exception ex) {
		String message = ex.getMessage() == null ? "" : ex.getMessage();
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.UN_CHECKED_EXCEPTION, message);
		return ResponseResult.builder(clientException.getCode(), clientException.getMessage());
	}
	private ResponseResult handleBindException(BindException bindException) {
		ClientBizException clientBizException =  new ClientBizException(ClientExceptionEnum.DATA_IS_NOT_VALID);
		Iterator<ObjectError> errorIt = bindException.getBindingResult().getAllErrors().iterator();
		StringBuilder errorBuilder = new StringBuilder(clientBizException.getMessage());
		while(errorIt.hasNext())  {
			errorBuilder.append(errorIt.next().getDefaultMessage());
			if (errorIt.hasNext()) {
				errorBuilder.append(",");
			}
		}
		return ResponseResult.builder(clientBizException.getCode(), errorBuilder.toString());
	}
	
	private ResponseResult handleClientBizException(ClientBizException ex) {
		return ResponseResult.builder(ex.getCode(), ex.getMessage());
	}
}
