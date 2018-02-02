package com.canaan.authorization.mapper;

import com.canaan.authorization.entity.SysRoleButton;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 角色-安全权限 接口
 * @author zening
 * @date 2018年2月2日 下午4:35:33
 * @version 1.0.0
 */
public interface SysRoleButtonMapper extends BaseMapper<SysRoleButton> {

	/**
	 * 删除角色对应的按钮关联
	 * @param roleId 角色id
	 * @return
	 */
	public int deleteByRoleId(@Param("roleId") int roleId);
	
}
