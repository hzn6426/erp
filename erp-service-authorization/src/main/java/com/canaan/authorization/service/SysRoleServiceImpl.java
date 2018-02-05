package com.canaan.authorization.service;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleService;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.authorization.entity.SysRole;
import com.canaan.authorization.mapper.SysRoleMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysRoleServiceImpl extends MBaseServiceImpl<SysRoleMapper, SysRole, RoleDTO> implements SysRoleService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRole> condition(RoleDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRole> primaryKeyCondition(RoleDTO v) {
		return new EntityWrapper<SysRole>().eq("id", v.getId());
	}

}
