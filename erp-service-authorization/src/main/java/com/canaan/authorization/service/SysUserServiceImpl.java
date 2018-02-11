package com.canaan.authorization.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.ListUtils;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleButtonService;
import com.canaan.authorization.api.SysRoleMenuService;
import com.canaan.authorization.api.SysUserRoleService;
import com.canaan.authorization.api.SysUserService;
import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.authorization.dto.UserDTO;
import com.canaan.authorization.entity.SysUser;
import com.canaan.authorization.mapper.SysUserMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.util.tool.Checker;
import com.canaan.util.tool.LoopUtil;

public class SysUserServiceImpl extends MBaseServiceImpl<SysUserMapper, SysUser, UserDTO> implements SysUserService {

	@Resource
	private SysUserRoleService userRoleService;
	
	@Resource
	private SysRoleButtonService roleButtonService;
	
	@Resource
	private SysRoleMenuService roleMenuService;
	
	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysUser> condition(UserDTO v) {
		return null;
	}

	@Override
	protected OrderBy orderby() {
		return null;
	}

	@Override
	protected Wrapper<SysUser> primaryKeyCondition(UserDTO v) {
		return new EntityWrapper<SysUser>().eq("id", v.getId());
	}

	@Override
	public List<String> listRoleNameByUserName(String userName) {
		List<String> list = new ArrayList<>(0);
		if (!Checker.BeNotEmpty(userName)) {
			return list;
		}
		List<RoleDTO> roleList = this.userRoleService.listRolesByUserName(userName);
		list = LoopUtil.listPropertyValue(roleList, "roleName");
		return list;
	}

	@Override
	public List<String> listPermsByUserName(String userName) {
		List<String> list = new ArrayList<>(0);
		if (!Checker.BeNotEmpty(userName)) {
			return list;
		}
		List<RoleDTO> roleList = this.userRoleService.listRolesByUserName(userName);
		List<Integer> roleIdList =  LoopUtil.listPropertyValue(roleList, "id");
		List<ButtonDTO> buttonList = this.roleButtonService.listButtonsByRoleIdList(roleIdList);
		List<String> buttonCodeList = LoopUtil.listPropertyValue(buttonList, "buttonCode");
		List<MenuDTO> menuList = this.roleMenuService.listMenusByRoleIdList(roleIdList);
		List<String> menuCodeList = LoopUtil.listPropertyValue(menuList, "menuCode");
		list = ListUtils.union(buttonCodeList, menuCodeList);
		return list;
	}

}
