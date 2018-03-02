package com.canaan.jgsf.exception;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.canaan.distribute.exception.BizException;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.util.WebUtil;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MVCExceptionHandler extends ExceptionHandlerExceptionResolver {
	private final static MVCExceptionHandler handler = new MVCExceptionHandler();
	
	private MVCExceptionHandler(){}
	
	public static void doHandlerException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!WebUtil.isAjaxRequest(request)) {
			response.sendRedirect(SystemConsts.SERVER_INTERNAL_ERROR);
			return;
		}
		ResponseResult<?> result = handler.createResultByExcepiton(ex);
		WebUtil.write(response, result);
		
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	protected Object handler404(NoHandlerFoundException ex, HttpServletRequest request) {
		ResponseResult<?> result = handleNotFundException((NoHandlerFoundException) ex);
		if (WebUtil.isAjaxRequest(request)) {
			return result;
		}
		ModelAndView mv = new ModelAndView(SystemConsts.REQUEST_NOT_FOUND);
		return mv;
	}
	@ExceptionHandler(Exception.class)
	protected Object handleCustomerException(Exception ex, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(SystemConsts.SERVER_INTERNAL_ERROR);
		if (!WebUtil.isAjaxRequest(request)) {
			return mv;
		}
		
		ResponseResult<?> result = createResultByExcepiton(ex);
		return result;
		
    }
	
	private ResponseResult<?> createResultByExcepiton(Exception ex) {
		ResponseResult<?> result = null;
		if (BizException.class.isInstance(ex)) {
			result = handleBizException((BizException) ex);
		} else if (DistributeException.class.isInstance(ex)) {
			result = handleDistributeException((DistributeException) ex);
		} else if (MethodArgumentNotValidException.class.isInstance(ex)) {
			result = handleBindException((MethodArgumentNotValidException) ex);
		} else if (BindException.class.isInstance(ex)) {
			result = handleBindException((BindException) ex);
		} else if (ClientBizException.class.isInstance(ex)) {
			result = handleClientBizException((ClientBizException) ex);
		} else if (NullPointerException.class.isInstance(ex)) {
			result =handleNullPointException((NullPointerException) ex);
		} else if (IllegalArgumentException.class.isInstance(ex)) {
			result =handleIllegalArgumentException((IllegalArgumentException) ex);
		} else if (ServletException.class.isInstance(ex)) {//for shiro exception
			Throwable cause = ((ServletException)ex).getCause();
			if (ClientBizException.class.isInstance(cause)) {
				result = handleClientBizException((ClientBizException) cause);
			} else {
				result = handleException((Exception)cause);
			}
		} else {
			result = handleException(ex);
		}
		return result;
	}
	
	private ResponseResult<?> handleIllegalArgumentException(IllegalArgumentException illex) {
		log.error("ClientBizException:" + Throwables.getStackTraceAsString(illex));
		String message = Optional.fromNullable(illex.getMessage()).or("");
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.ILLEGAL_ARGUMENT, message);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult<?> handleNullPointException(NullPointerException npe) {
		log.error("ClientBizException:" + Throwables.getStackTraceAsString(npe));
		String message = Optional.fromNullable(npe.getMessage()).or("");
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.OBJECT_IS_NULL, message);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult<?> handleNotFundException(NoHandlerFoundException nfe) {
		log.error("ClientBizException:" + Throwables.getStackTraceAsString(nfe));
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.REQUEST_NOT_FOUND);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult<?> handleBizException(BizException bizException) {
		log.error("BizException(" + bizException.getUuid() + "):" + bizException.getStackMessage());
		return ResponseResult.build(bizException.getCode(), bizException.getMessage());
	}
	
	private ResponseResult<?> handleDistributeException(DistributeException distributeException) {
		log.error("DistributeException:" + distributeException.getMessage());
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.DISTRIBUTE_EXCEPTION);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult<?> handleException(Exception ex) {
		String message = Optional.fromNullable(ex.getMessage()).or("");
		log.info("ClientBizException:" + message);
		ClientBizException clientException = new ClientBizException(ClientExceptionEnum.UN_CHECKED_EXCEPTION, message);
		return ResponseResult.build(clientException.getCode(), clientException.getMessage());
	}
	
	private ResponseResult<?> handleCommonBindException(BindingResult bindResult) {
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
	
	private ResponseResult<?> handleBindException(MethodArgumentNotValidException methodArgumentException) {
		log.error("ClientBizException:" + Throwables.getStackTraceAsString(methodArgumentException));
		return handleCommonBindException(methodArgumentException.getBindingResult());
	}
	private ResponseResult<?> handleBindException(BindException bindException) {
		log.error("ClientBizException:" + Throwables.getStackTraceAsString(bindException));
		return handleCommonBindException(bindException.getBindingResult());
		
	}
	
	private ResponseResult<?> handleClientBizException(ClientBizException ex) {
		log.error(Throwables.getStackTraceAsString(ex));
		return ResponseResult.build(ex.getCode(), ex.getMessage());
	}

}
