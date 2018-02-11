package com.canaan.authorization.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleMenuService;
import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.dto.RoleMenuDTO;
import com.canaan.authorization.entity.SysMenu;
import com.canaan.authorization.entity.SysRoleMenu;
import com.canaan.authorization.mapper.SysRoleMenuMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;

public class SysRoleMenuServiceImpl extends MBaseServiceImpl<SysRoleMenuMapper, SysRoleMenu, RoleMenuDTO> implements SysRoleMenuService {

	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysRoleMenu> condition(RoleMenuDTO v) {
		return null;
	}

	@Override
	protected OrderBy orderby() {
		return null;
	}

	@Override
	protected Wrapper<SysRoleMenu> primaryKeyCondition(RoleMenuDTO v) {
		return new EntityWrapper<SysRoleMenu>().eq("id", v.getId());
	}
	
	

	@Override
	public List<MenuDTO> listChildMenusByIdAndRoleIdList(Integer menuId, List<Integer> roleIdList) {
		Assert.CheckArgument(menuId);
		List<MenuDTO> list = new ArrayList<>(0);
		
		if (Checker.BeNotEmpty(roleIdList)) {
			Map<String, Object> map = new HashMap<>(2);
			map.put("menuId", menuId);
			map.put("list", roleIdList);
			List<SysMenu> menuList = this.baseMapper.listChildMenuByMapMenuIdAndRoleIds(map);
			if (Checker.BeNotEmpty(menuList)) {
				list = new ArrayList<>(this.collectionMapper.mapCollection(menuList, MenuDTO.class));
			}
		}
		
		return list;
	}

	@Override
	public List<MenuDTO> listMenusByRoleIdList(List<Integer> roleIdList) {
		List<MenuDTO> list = new ArrayList<>(0);
		
		if (Checker.BeNotEmpty(roleIdList)) {
			List<SysMenu> menuList = this.baseMapper.listMenuByRoleIdList(roleIdList);
			if (Checker.BeNotEmpty(menuList)) {
				list = new ArrayList<>(this.collectionMapper.mapCollection(menuList, MenuDTO.class));
			}
		}
		
		return list;
	}

	@Override
	public void deleMenusByRoleId(Integer roleId) {
		Assert.CheckArgument(roleId);
		this.baseMapper.deleteByRoleId(roleId);
	}

	@Override
	public void deleteMenusByRoleIdAndMenuIdList(Integer roleId, List<Integer> idList) {
		Assert.CheckArgumentStrict(roleId, idList);
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		map.put("menuIdList", idList);
		this.baseMapper.deleteByMapRoleIdAndMenuIds(map);
		
	}

	@Override
	public void addMenusByRoleIdAndMenuIdList(Integer roleId, List<Integer> idList) {
		Assert.CheckArgumentStrict(roleId, idList);
		List<SysRoleMenu> list = new ArrayList<>(idList.size());
		for (Integer menuId : idList) {
			list.add(new SysRoleMenu().setMenuId(menuId).setRoleId(roleId));
		}
		this.insertBatch(list);
		
	}
	
	

}
