package com.canaan.jgsf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "用户管理")
@RestController
public class UserController {

	@ApiOperation(value="新增")
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String add(@RequestBody @Validated UserVO user) {
		return ResponseResult.build().json();
	}
	
	@ApiOperation(value="更新")
	@RequestMapping(value="/user",method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void update() {
//		return ResponseResult.build().json();
	}
	@ApiOperation(value="查询")
	@RequestMapping(value="/user",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserVO> get() {
		List<UserVO> userList = new ArrayList<UserVO>(2);
//		userList.add(UserVO.builder()
//				.address(null).birth(new DateTime().toDate())
//				.id("37028219880101").name("john").tel("15166059493").build());
//		userList.add(UserVO.builder()
//				.address("青岛市李沧区京口路1号").birth(new DateTime().toDate())
//				.id("37028219880201").name("jerry").tel("15166234493").build());
		return userList;
	}
	
	@ApiOperation(value="删除")
	@RequestMapping(value="/user",method=RequestMethod.DELETE)
	public String delete(){
		return ResponseResult.build().json();
	}
}
