package com.canaan.authorization.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysButtonService;
import com.canaan.authorization.api.SysMenuService;
import com.canaan.authorization.constant.AuthorizationExceptionEnum;
import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.entity.SysButton;
import com.canaan.authorization.mapper.SysButtonMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;

public class SysButtonServiceImpl extends MBaseServiceImpl<SysButtonMapper, SysButton, ButtonDTO> implements SysButtonService {

	@Resource
	private SysMenuService menuService;
	
	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysButton> condition(ButtonDTO v) {
		Assert.CheckArgument(v);
		EntityWrapper<SysButton> wrapper = new EntityWrapper<>();
		wrapper.like(StringUtils.isNoneBlank(v.getButtonCode()), "button_code", v.getButtonCode() + "%")
			.like(StringUtils.isNoneBlank(v.getButtonName()), "button_name", v.getButtonName())
			.eq(Checker.BeNotNull(v.getMenuId()), "menu_id", v.getMenuId());
		return wrapper;
	}

	@Override
	protected OrderBy orderby() {
		return new OrderBy().desc("id");
	}

	@Override
	protected Wrapper<SysButton> primaryKeyCondition(ButtonDTO v) {
		return new EntityWrapper<SysButton>().eq("id", v.getId());
	}

	@Override
	public void deleteByIdList(List<Integer> idList) {
		Assert.CheckArgument(idList);
		this.baseMapper.deleteBatchIds(idList);
		
	}

	@Override
	public void save(ButtonDTO v) {
		Assert.CheckArgument(v, v.getMenuId());
		//检查关联的菜单是否存在
		Assert.CheckNotNull(menuService.get(v.getMenuId()), AuthorizationExceptionEnum.MENU_OF_BUTTON_REFERENCE_NOT_EXIST); 
		super.save(v);
	}

	@Override
	public void update(ButtonDTO v) {
		Assert.CheckArgument(v, v.getMenuId());
		//检查关联的菜单是否存在
		Assert.CheckNotNull(menuService.get(v.getMenuId()), AuthorizationExceptionEnum.MENU_OF_BUTTON_REFERENCE_NOT_EXIST);
		super.update(v);
	}

	
}
