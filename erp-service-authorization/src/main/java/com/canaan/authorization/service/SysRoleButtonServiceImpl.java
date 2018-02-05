package com.canaan.authorization.service;


import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleButtonService;
import com.canaan.authorization.dto.RoleButtonDTO;
import com.canaan.authorization.entity.SysRoleButton;
import com.canaan.authorization.mapper.SysRoleButtonMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysRoleButtonServiceImpl extends MBaseServiceImpl<SysRoleButtonMapper, SysRoleButton, RoleButtonDTO> 
	implements SysRoleButtonService {

	

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRoleButton> condition(RoleButtonDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysRoleButton> primaryKeyCondition(RoleButtonDTO v) {
		return new EntityWrapper<SysRoleButton>().eq("id", v.getId());
	}


}
