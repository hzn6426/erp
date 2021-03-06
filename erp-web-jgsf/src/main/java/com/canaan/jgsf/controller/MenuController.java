package com.canaan.jgsf.controller;



import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.authorization.dto.MenuDTO;
import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.MBaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;
import com.canaan.util.tool.Checker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "菜单管理", description="菜单的增删改查功能!")
@RestController
@RequestMapping(value="/menu", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MenuController extends MBaseController<MenuDTO> {

	
	@ApiOperation(value = "查询子节点")
	@RequestMapping(value = "listByParent", method = RequestMethod.GET)
	public ResponseResult<MenuDTO> listByParentId(@RequestParam Integer parentId) {
		if (!Checker.BeNotNull(parentId)) {
			parentId = -1;
		}
		MenuDTO menu = new MenuDTO();
		menu.setParentId(parentId);
		List<MenuDTO> list = super.list(menu);
		return ResponseResult.build(list);
	}
	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<MenuDTO> list(@RequestParam int page, @RequestParam int limit) {
		MenuDTO menu = new MenuDTO();
		SearchResult<MenuDTO> menuResult = super.list(menu, page, limit);
		return ResponseResult.build(menuResult.getTotalSize(), menuResult.getDataList());
	}
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MenuDTO get(@PathVariable Integer id) {
		return super.get(id);
	}
	
	@ApiOperation(value = "保存")
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody MenuDTO menu) {
		Assert.CheckArgument(menu);
		super.save(menu);
	}
	
	@ApiOperation(value = "更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody MenuDTO menu) {
		Assert.CheckArgument(menu);
		super.update(menu);
	}
	
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		Assert.CheckArgument(id);
		MenuDTO menu = new MenuDTO();
		menu.setId(id);
		super.delete(menu);
	}
	
	@ApiOperation(value = "批量删除")
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestParam List<Integer> ids) {
		Assert.CheckArgument(ids);
		super.deleteBatch(ids);
	}
}
