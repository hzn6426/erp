package com.canaan.authorization.service;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysUserRoleService;
import com.canaan.authorization.dto.UserRoleDTO;
import com.canaan.authorization.entity.SysUserRole;
import com.canaan.authorization.mapper.SysUserRoleMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysUserRoleServiceImpl extends MBaseServiceImpl<SysUserRoleMapper, SysUserRole, UserRoleDTO> implements SysUserRoleService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysUserRole> condition(UserRoleDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysUserRole> primaryKeyCondition(UserRoleDTO v) {
		return new EntityWrapper<SysUserRole>().eq("id", v.getId());
	}

}
