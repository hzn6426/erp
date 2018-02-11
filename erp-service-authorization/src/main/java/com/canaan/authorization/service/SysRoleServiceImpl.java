package com.canaan.authorization.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.functors.IfClosure;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysButtonService;
import com.canaan.authorization.api.SysMenuService;
import com.canaan.authorization.api.SysRoleButtonService;
import com.canaan.authorization.api.SysRoleMenuService;
import com.canaan.authorization.api.SysRoleService;
import com.canaan.authorization.constant.AuthorizationExceptionEnum;
import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.dto.MenuButtonTreeDTO;
import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.authorization.entity.SysRole;
import com.canaan.authorization.mapper.SysRoleMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.exception.ServerException;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;
import com.canaan.util.tool.LoopUtil;
import com.google.common.base.Splitter;

public class SysRoleServiceImpl extends MBaseServiceImpl<SysRoleMapper, SysRole, RoleDTO> implements SysRoleService {

	@Resource
	private SysMenuService menuService;
	
	@Resource
	private SysRoleMenuService roleMenuService;
	
	@Resource
	private SysRoleButtonService roleButtonService;
	
	@Resource
	private SysButtonService buttonService;
	
	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysRole> condition(RoleDTO v) {
		return null;
	}

	@Override
	protected OrderBy orderby() {
		return null;
	}

	@Override
	protected Wrapper<SysRole> primaryKeyCondition(RoleDTO v) {
		return new EntityWrapper<SysRole>().eq("id", v.getId());
	}
	
	
	
	@Override
	public void saveRolePerms(List<String> menuButtonTreeIdList, Integer roleId) {
		Assert.CheckArgument(roleId);
		
		//列表为空表示清空授权
		if (!Checker.BeNotEmpty(menuButtonTreeIdList)) {
			roleMenuService.deleMenusByRoleId(roleId);
			roleButtonService.deleteButtonsByRoleId(roleId);
			return;
		}
		RoleDTO role = this.get(roleId);
		if(!Checker.BeNotNull(role)) {
			throw new ServerException(AuthorizationExceptionEnum.INVALID_ROLE_ID_EXCEPTION, roleId);
		}
		List<Integer> roleIdList = new ArrayList<>(1);
		roleIdList.add(roleId);
		//获取原始菜单权限，提取权限对应的菜单id列表
		List<MenuDTO> menuList = roleMenuService.listMenusByRoleIdList(roleIdList);
		List<Integer> menuIdList = LoopUtil.listPropertyValue(menuList, "id");
		//获取原始按钮权限，提取权限对应的按钮id列表
		List<ButtonDTO> buttonList = roleButtonService.listButtonsByRoleIdList(roleIdList);
		List<Integer> buttonIdList = LoopUtil.listPropertyValue(buttonList, "id");
		//判断当前授权的treeid是否是按钮id
		Predicate<String> buttonPredicate = new Predicate<String>() {
			@Override
			public boolean evaluate(String menuButtonTreeId) {
				if (menuButtonTreeId.indexOf("btn_") == -1 && menuButtonTreeId.indexOf("menu_") == -1) {
					throw new ServerException(AuthorizationExceptionEnum.INVALID_NODE_ID_EXCEPTION, menuButtonTreeId);
				}
				return menuButtonTreeId.indexOf("btn_") != -1;
			}
		};
		//当前授权的按钮id列表
		List<Integer> authButtonIdList = new ArrayList<>();
		//当前授权的菜单id列表
		List<Integer> authMenuIdList = new ArrayList<>();
		//筛选按钮id和菜单id列表
		Closure<String> buttonClosure = new Closure<String>() {
			@Override
			public void execute(String input) {
				authButtonIdList.add(Integer.valueOf(Splitter.on("_").splitToList(input).get(1)));
			}
		};
		
		Closure<String> menuClosure = new Closure<String>() {
			@Override
			public void execute(String input) {
				authMenuIdList.add(Integer.valueOf(Splitter.on("_").splitToList(input).get(1)));
			}
		};
		Closure<String> ifClosure = IfClosure.ifClosure(buttonPredicate, buttonClosure, menuClosure);
		IterableUtils.forEach(menuButtonTreeIdList, ifClosure);
		/*
		 * 处理按钮（菜单处理相同）：
		 * 1.以当前授权按钮id集合为全集，以原始按钮id集合为子集，进行补集运算，结果为 需要添加的按钮权限，
		 * 2.以原始按钮权限id集合为全集，以当前授权按钮id集合为子集，进行补集运算，结果为需要删除的按钮权限
		 */
		List<Integer> toDeleteButtonIdList = ListUtils.subtract(buttonIdList, authButtonIdList);
		List<Integer> toAddButtonIdList = ListUtils.subtract(authButtonIdList, buttonIdList);
		if (Checker.BeNotEmpty(toDeleteButtonIdList)) {
			roleButtonService.deleteButtonsByRoleIdAndButtonIdList(roleId, toDeleteButtonIdList);
		}
		if (Checker.BeNotEmpty(toAddButtonIdList)) {
			roleButtonService.addButtonsByRoleIdAndButtonIdList(roleId, toAddButtonIdList);
		}
		
		List<Integer> toDeleteMenuIdList = ListUtils.subtract(menuIdList, authMenuIdList);
		List<Integer> toAddMenuIdList = ListUtils.subtract(authMenuIdList, menuIdList);
		if (Checker.BeNotEmpty(toDeleteMenuIdList)) {
			roleMenuService.deleteMenusByRoleIdAndMenuIdList(roleId, toDeleteMenuIdList);
		}
		if (Checker.BeNotEmpty(toAddMenuIdList)) {
			roleMenuService.addMenusByRoleIdAndMenuIdList(roleId, toAddMenuIdList);
		}
		
	}

	@Override
	public List<MenuButtonTreeDTO> listLazyMenusOrButtonsByRoleIdList(List<Integer> roleIdList, String nodeParentId) {
		List<MenuButtonTreeDTO> list = new ArrayList<>(0);
		if (!Checker.BeNotEmpty(roleIdList)) {
			return list;
		}
		
		int parentId = -1;
		//处理nodeParentId，如果为null或空串表示读取一级菜单（parentId为-1）
		if (Checker.BeNotBlank(nodeParentId)) {
			if (nodeParentId.indexOf("menu") != -1) {
				parentId = Integer.valueOf(Splitter.on("_").splitToList(nodeParentId).get(1));
			}
			
		}
		
		List<MenuDTO> menuList = menuService.list(new MenuDTO().setParentId(parentId));
		//子菜单为空，获取按钮，不为空判断子菜单的授权信息，进行封装
		if (Checker.BeNotEmpty(menuList)) {
			List<MenuDTO> authMenuList = roleMenuService.listChildMenusByIdAndRoleIdList(parentId, roleIdList);
			List<Integer> authMenuIdList = LoopUtil.listPropertyValue(authMenuList, "id");
			for (MenuDTO menu : menuList) {
				list.add(new MenuButtonTreeDTO()
						.setNodeId("menu_" + menu.getId())
						.setNodeName(menu.getMenuName())
						.setNodeType("menu")
						.setChecked(authMenuIdList.contains(menu.getId())));
			}
		} else {
			List<ButtonDTO> buttonList = buttonService.list(new ButtonDTO().setMenuId(parentId));
			if (Checker.BeNotEmpty(buttonList)) {
				List<ButtonDTO> authButtonList = roleButtonService.listButtonsByRoleIdList(roleIdList);
				List<Integer> authButtonIdList = LoopUtil.listPropertyValue(authButtonList, "id");
				for (ButtonDTO button : buttonList) {
					list.add(new MenuButtonTreeDTO()
							.setNodeId("btn_" + button.getId())
							.setNodeName(button.getButtonName())
							.setNodeType("btn")
							.setChecked(authButtonIdList.contains(button.getId())));
				}
			}
			
		}
		return list;
	}

}
