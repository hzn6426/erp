package com.canaan.jgsf.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
@RestController
public class NotFoundController {

	@RequestMapping(value = {"/**"})
	public void handle404() {
		throw new ClientBizException(ClientExceptionEnum.REQUEST_NOT_FOUND);
	}
}
