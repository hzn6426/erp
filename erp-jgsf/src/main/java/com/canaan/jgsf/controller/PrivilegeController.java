package com.canaan.jgsf.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.privilege.dto.PrivilegeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
@Api(tags = "权限管理", description="权限测试")
@Slf4j
@RestController
@RequestMapping(value="/privilege", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PrivilegeController {

	@ApiOperation("保存权限信息")
	@RequestMapping(method = RequestMethod.POST)
	public void save(@RequestBody PrivilegeDTO privilege) {
		log.info(privilege.toString());
	}
}
