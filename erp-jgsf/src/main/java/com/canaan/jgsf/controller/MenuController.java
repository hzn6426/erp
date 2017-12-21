package com.canaan.jgsf.controller;


import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.vo.MenuVO;
import com.canaan.privilege.dto.MenuDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理")
@RestController("/menu")
//@RequestMapping("/menu")
public class MenuController  {

	
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseResult list() {
//		MenuDTO menu = new MenuDTO();
//		SearchResult<MenuDTO> menuResult = super.list(menu, 1, 10);
		SearchResult<MenuDTO> menuResult = new SearchResult<>(0,new ArrayList<MenuDTO>());
		return ResponseResult.build(menuResult.getTotalSize(), menuResult.getDataList());
	}
	
	@ApiOperation(value = "新增")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public void add(@RequestBody MenuVO menu) {
		throw new ClientBizException(ClientExceptionEnum.ILLEGAL_ARGUMENT);
	}
}
