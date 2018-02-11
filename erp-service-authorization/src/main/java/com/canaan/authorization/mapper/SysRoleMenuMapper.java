package com.canaan.authorization.mapper;

import com.canaan.authorization.entity.SysMenu;
import com.canaan.authorization.entity.SysRoleMenu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 角色菜单接口
 * @author zening
 * @date 2018年2月2日 下午5:07:29
 * @version 1.0.0
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	
	/**
	 * 根据角色id列表及<code>menuId</code>获取对应权限的子菜单
	 * @param menuId 菜单id
	 * @param roleIdList 角色id列表
	 * @return 角色对应子菜单列表
	 */
	List<SysMenu> listChildMenuByMapMenuIdAndRoleIds(Map<String, Object> map);
	
	/**
	 * 根据角色列表获取菜单列表
	 * @param roleIdList 角色id列表
	 * @return 角色对应菜单列表
	 */
	List<SysMenu> listMenuByRoleIdList(List<Integer> roleIdList);
	
	/**
	 * 删除角色对应的菜单关联
	 * @param roleId 角色id
	 * @return 删除的行数
	 */
	int deleteByRoleId(@Param("roleId") int roleId);
	
	/**
	 * 删除角色id对应的菜单id列表的菜单权限
	 * @param map 角色id与 菜单id 封装
	 * @return
	 */
	int deleteByMapRoleIdAndMenuIds(Map<String, Object> map);
}
