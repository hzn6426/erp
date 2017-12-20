package com.canaan.jgsf.exception;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.WebUtil;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/404";
    }
	@ExceptionHandler(value = ClientBizException.class)
	public ResponseResult defaultExceptionHandler(ClientBizException clientException) throws Exception {
		log.error(Throwables.getStackTraceAsString(clientException));
		ResponseResult result = handleClientBizException(clientException);
		return result;
	}
	@ExceptionHandler(value = BindException.class)
	public ResponseResult validateBindExceptionHandler(BindException bindException) {
		ResponseResult result = handleBindException(bindException);
		return result;
	}
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseResult validateArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentException) {
		ResponseResult result = handleBindException(methodArgumentException);
		return result;
	}
	@ExceptionHandler(value = DistributeException.class)
	public ResponseResult DistributeExceptionHandler(DistributeException distributeException) {
		ResponseResult result =  handleDistributeException(distributeException);
		return result;
	}
	@ExceptionHandler(value = BizException.class)
	public ResponseResult BizExceptionHandler(BizException bizException) {
		ResponseResult result =  handleBizException(bizException);
		return result;
	}
	@ExceptionHandler(value = {NullPointerException.class, IllegalArgumentException.class})
	public ResponseResult CommonRunTimeExceptionHandler(RuntimeException rex) {
		if (NullPointerException.class.isInstance(rex)) {
			String message = Optional.fromNullable(rex.getMessage()).or("");
			throw new ClientBizException(ClientExceptionEnum.OBJECT_IS_NULL,message);
		} else if (IllegalArgumentException.class.isInstance(rex)) {
			String message = Optional.fromNullable(rex.getMessage()).or("");
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
		log.error("BizException(" + bizException.getUuid() + "):" + Throwables.getStackTraceAsString(Throwables.getRootCause(bizException)));
		return ResponseResult.build(bizException.getCode(), bizException.getMessage());
	}
	
	private ResponseResult handleDistributeException(DistributeException distributeException) {
		log.error("DistributeException:" + distributeException.getMessage());
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.DISTRIBUTE_EXCEPTION);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult handleException(Exception ex) {
		String message = Optional.fromNullable(ex.getMessage()).or("");
		log.info(message);
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.UN_CHECKED_EXCEPTION, message);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult handleCommonBindException(BindingResult bindResult) {
		ClientBizException clientBizException =  new ClientBizException(ClientExceptionEnum.DATA_IS_NOT_VALID);
		Iterator<ObjectError> errorIt = bindResult.getAllErrors().iterator();
		StringBuilder errorBuilder = new StringBuilder(clientBizException.getMessage());
		while(errorIt.hasNext())  {
			errorBuilder.append(errorIt.next().getDefaultMessage());
			if (errorIt.hasNext()) {
				errorBuilder.append(",");
			}
		}
		
		return ResponseResult.build(clientBizException.getCode(), errorBuilder.toString());
	}
	private ResponseResult handleBindException(MethodArgumentNotValidException methodArgumentException) {
		log.error(Throwables.getStackTraceAsString(methodArgumentException));
		return handleCommonBindException(methodArgumentException.getBindingResult());
	}
	private ResponseResult handleBindException(BindException bindException) {
		log.error(Throwables.getStackTraceAsString(bindException));
		return handleCommonBindException(bindException.getBindingResult());
		
	}
	
	private ResponseResult handleClientBizException(ClientBizException ex) {
		return ResponseResult.build(ex.getCode(), ex.getMessage());
	}
}
