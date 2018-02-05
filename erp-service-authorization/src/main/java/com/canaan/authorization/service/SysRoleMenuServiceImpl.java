package com.canaan.authorization.service;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleMenuService;
import com.canaan.authorization.dto.RoleMenuDTO;
import com.canaan.authorization.entity.SysRoleMenu;
import com.canaan.authorization.mapper.SysRoleMenuMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysRoleMenuServiceImpl extends MBaseServiceImpl<SysRoleMenuMapper, SysRoleMenu, RoleMenuDTO> implements SysRoleMenuService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRoleMenu> condition(RoleMenuDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRoleMenu> primaryKeyCondition(RoleMenuDTO v) {
		return new EntityWrapper<SysRoleMenu>().eq("id", v.getId());
	}

}
