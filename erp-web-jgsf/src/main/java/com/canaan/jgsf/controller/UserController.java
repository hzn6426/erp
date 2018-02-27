package com.canaan.jgsf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.authorization.api.SysUserService;
import com.canaan.authorization.dto.UserDTO;
import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.MBaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "用户管理", description = "用户的增删改查功能!")
@RequestMapping(value="/user", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class UserController extends MBaseController<UserDTO> {

	@Resource
	private SysUserService userService;
	
	
	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<UserDTO> list(@RequestParam int page, @RequestParam int limit) {
		UserDTO user = new UserDTO();
		SearchResult<UserDTO> userResult = super.list(user, page, limit);
		return ResponseResult.build(userResult.getTotalSize(), userResult.getDataList());
	}
	
	@ApiOperation(value="新增")
	@RequestMapping(method=RequestMethod.POST)
	public void add(@RequestBody @Validated UserDTO user) {
		Assert.CheckArgument(user);
		super.save(user);
	}
	
	@ApiOperation(value="更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody @Validated UserDTO user) {
		Assert.CheckArgument(user);
		super.update(user);
	}
	@ApiOperation(value="查询")
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public UserDTO get(@PathVariable Integer id) {
		Assert.CheckArgument(id);
		return super.get(id);
	}
	
	@ApiOperation(value="删除")
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		Assert.CheckArgument(id);
		super.delete(new UserDTO().setId(id));
	}
	
	@ApiOperation(value = "批量删除")
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestParam List<Integer> ids) {
		Assert.CheckArgument(ids);
		super.deleteBatch(ids);
	}
}
