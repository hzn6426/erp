package com.canaan.jgsf.controller;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.MBaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "按钮管理", description="按钮的增删改查功能!")
@RequestMapping(value = "/button", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class ButtonController extends MBaseController<ButtonDTO> {

	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<ButtonDTO> list(@RequestParam int page, @RequestParam int limit) {
		ButtonDTO button = new ButtonDTO();
		SearchResult<ButtonDTO> buttonResult = super.list(button, page, limit);
		return ResponseResult.build(buttonResult.getTotalSize(), buttonResult.getDataList());
	}
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ButtonDTO get(@PathVariable Integer id) {
		return super.get(id);
	}
	
	@ApiOperation(value = "保存")
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody ButtonDTO button) {
		Assert.CheckArgument(button);
		super.save(button);
	}
	
	@ApiOperation(value = "更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody ButtonDTO button) {
		Assert.CheckArgument(button);
		super.update(button);
	}
	
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		Assert.CheckArgument(id);
		super.delete(new ButtonDTO().setId(id));
	}
	
	@ApiOperation(value = "批量删除")
	@RequestMapping(method = RequestMethod.DELETE)
	public void delete(@RequestParam List<Integer> ids) {
		Assert.CheckArgument(ids);
		super.deleteBatch(ids);
	}
}
