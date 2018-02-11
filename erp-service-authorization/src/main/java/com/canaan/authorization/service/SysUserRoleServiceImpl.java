package com.canaan.authorization.service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysUserRoleService;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.authorization.dto.UserRoleDTO;
import com.canaan.authorization.entity.SysRole;
import com.canaan.authorization.entity.SysUserRole;
import com.canaan.authorization.mapper.SysUserRoleMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;

public class SysUserRoleServiceImpl extends MBaseServiceImpl<SysUserRoleMapper, SysUserRole, UserRoleDTO> implements SysUserRoleService {

	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysUserRole> condition(UserRoleDTO v) {
		return null;
	}

	@Override
	protected OrderBy orderby() {
		return null;
	}

	@Override
	protected Wrapper<SysUserRole> primaryKeyCondition(UserRoleDTO v) {
		return new EntityWrapper<SysUserRole>().eq("id", v.getId());
	}

	@Override
	public List<RoleDTO> listRolesByUserName(String userName) {
		
		List<RoleDTO> list = new ArrayList<>(0);
		//验证参数
		if (!Checker.BeNotBlank(userName)) {
			return list;
		}
		//查询
		List<SysRole> roleList = this.baseMapper.listRoleByUserName(userName);
		if (!Checker.BeNotEmpty(roleList)) {
			return list;
		}
		//转换
		list = new ArrayList<>(this.collectionMapper.mapCollection(roleList, RoleDTO.class));
		
		return list;
	}

	@Override
	public int deleteRolesByUserId(Integer userId) {
		Assert.CheckArgument(userId);
		return this.deleteRolesByUserId(userId);
	}
	
	

}
