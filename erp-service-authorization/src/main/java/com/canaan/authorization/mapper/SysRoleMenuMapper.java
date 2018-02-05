package com.canaan.authorization.mapper;

import com.canaan.authorization.entity.SysRoleMenu;

import java.util.List;

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
	 * 根据角色列表获取菜单列表
	 * @param roleIdList 角色id列表
	 * @return
	 */
	public List<SysRoleMenu> listMenuByRoleIdList(List<Integer> roleIdList);
	
	/**
	 * 删除角色对应的菜单关联
	 * @param roleId 角色id
	 * @return
	 */
	public int deleteByRoleId(@Param("roleId") int roleId);
}
