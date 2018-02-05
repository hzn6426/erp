package com.canaan.authorization.service;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysUserService;
import com.canaan.authorization.dto.UserDTO;
import com.canaan.authorization.entity.SysUser;
import com.canaan.authorization.mapper.SysUserMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysUserServiceImpl extends MBaseServiceImpl<SysUserMapper, SysUser, UserDTO> implements SysUserService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysUser> condition(UserDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysUser> primaryKeyCondition(UserDTO v) {
		return new EntityWrapper<SysUser>().eq("id", v.getId());
	}

}
