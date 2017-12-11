package com.canaan.jgsf.controller;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.BaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.privilege.dto.MenuDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "菜单管理")
@RestController
@RequestMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController extends BaseController<MenuDTO> {

	@Resource
	private Mapper mapper;
	
	@ApiOperation(value = "查询")
	@RequestMapping("/list")
	public ResponseResult list() {
		MenuDTO menu = new MenuDTO();
		SearchResult<MenuDTO> menuResult = super.list(menu, 1, 10);
		return ResponseResult.builder(menuResult.getTotalSize(), menuResult.getDataList());
	}
}
