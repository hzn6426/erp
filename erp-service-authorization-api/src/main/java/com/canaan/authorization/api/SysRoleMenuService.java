package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.MenuDTO;
import com.canaan.authorization.dto.RoleMenuDTO;
import com.canaan.common.MBaseService;
/**
 * 角色菜单服务，提供角色菜单权限关系相关服务
 * @author zening
 * @date 2018年2月6日 上午11:45:09
 * @version 1.0.0
 */
public interface SysRoleMenuService extends MBaseService<RoleMenuDTO> {

	
	/**
	 * 获取角色列表及菜单id对应的有权限的子菜单列表
	 * @param menuId 菜单id
	 * @param roleIdList 角色列表
	 * @return 角色对应的子菜单列表
	 */
	List<MenuDTO> listChildMenusByIdAndRoleIdList(Integer menuId, List<Integer> roleIdList);
	/**
	 * 根据角色id列表获取所有菜单列表
	 * @param roleIdList
	 * @return 空列表或角色对应的菜单列表
	 */
	List<MenuDTO> listMenusByRoleIdList(List<Integer> roleIdList);
	
	/**
	 * 删除角色对应的菜单权限
	 * @param roleId 角色id
	 * @return 删除的行数，<code>roleId</code>为null时抛出 {@link IllegalArgumentException}
	 */
	void deleMenusByRoleId(Integer roleId);
	
	/**
	 * 删除角色id的对应id列表的菜单权限列表
	 * @param roleId 角色id
	 * @param idList 菜单id列表
	 */
	void deleteMenusByRoleIdAndMenuIdList(Integer roleId, List<Integer> idList);
	
	/**
	 * 添加角色id的对应id列表的菜单权限列表
	 * @param roleId 角色id
	 * @param idList 菜单id列表
	 */
	void addMenusByRoleIdAndMenuIdList(Integer roleId, List<Integer> idList);
	
	
}
