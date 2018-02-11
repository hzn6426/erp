package com.canaan.authorization.service;


import java.util.List;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysMenuService;
import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.entity.SysMenu;
import com.canaan.authorization.mapper.SysMenuMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;

public class SysMenuServiceImpl extends MBaseServiceImpl<SysMenuMapper, SysMenu, MenuDTO> implements SysMenuService {

	
	
	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysMenu> condition(MenuDTO v) {
		Assert.CheckArgument(v);
		EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
		wrapper.eq(Checker.BeNotNull(v.getParentId()), "parent_id", v.getParentId());
		return wrapper;
	}

	@Override
	protected OrderBy orderby() {
		return new OrderBy().asc("priority");
	}

	@Override
	protected Wrapper<SysMenu> primaryKeyCondition(MenuDTO v) {
		return new EntityWrapper<SysMenu>().eq("id", v.getId());
	}

	@Override
	public void deleteByIdList(List<Integer> idList) {
		Assert.CheckArgument(idList);
		this.baseMapper.deleteBatchIds(idList);
	}


	
}
