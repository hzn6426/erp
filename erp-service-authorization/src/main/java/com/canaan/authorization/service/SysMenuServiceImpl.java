package com.canaan.authorization.service;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysMenuService;
import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.entity.SysMenu;
import com.canaan.authorization.mapper.SysMenuMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysMenuServiceImpl extends MBaseServiceImpl<SysMenuMapper, SysMenu, MenuDTO> implements SysMenuService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysMenu> condition(MenuDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysMenu> primaryKeyCondition(MenuDTO v) {
		return new EntityWrapper<SysMenu>().eq("id", v.getId());
	}

}
