package com.canaan.jgsf.controller;



import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.common.SearchResult;
import com.canaan.distribute.common.Distribute;
import com.canaan.jgsf.common.BaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;
import com.canaan.privilege.api.SysUserService;
import com.canaan.privilege.dto.MenuDTO;
import com.canaan.privilege.dto.UserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理", description="菜单的增删改查功能!")
@RestController
@RequestMapping(value="/menu", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController extends BaseController<MenuDTO> {

	@Resource
	private SysUserService userService;
	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<MenuDTO> list(@RequestParam int pageNumber, @RequestParam int pageSize) {
		MenuDTO menu = new MenuDTO();
		SearchResult<MenuDTO> menuResult = super.list(menu, pageNumber, pageSize);
		return ResponseResult.build(menuResult.getTotalSize(), menuResult.getDataList());
	}
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MenuDTO get(@PathVariable Integer id) {
		MenuDTO menu = new MenuDTO();
		menu.setId(id);
		return super.get(menu);
	}
	
	@ApiOperation(value = "保存")
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody MenuDTO menu) {
		Assert.checkArgument(menu);
		super.save(menu);
	}
	
	@Distribute
	@ApiOperation(value = "更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody MenuDTO menu) {
		Assert.checkArgument(menu);
		super.update(menu);
		UserDTO udto = new UserDTO();
		userService.save(udto);
	}
	
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		Assert.checkArgument(id);
		MenuDTO menu = new MenuDTO();
		menu.setId(id);
		super.delete(menu);
	}
}
