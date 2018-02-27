package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.UserDTO;
import com.canaan.common.MBaseService;

public interface SysUserService extends MBaseService<UserDTO> {

	/**
	 * 根据用户名获取角色名列表
	 * @param userName 用户名
	 * @return 角色名列表，没有返回空列表
	 */
	List<String> listRoleNameByUserName(String userName);
	
	/**
	 * 根据用户名获取权限字符串列表，包括<b>菜单</b>,<b>按钮</b>权限
	 * @param userName 用户名
	 * @return 权限列表，没有返回空列表
	 */
	List<String> listPermsByUserName(String userName);
	
	
	/**
	 * 根据用户名获取用户信息
	 * @param userName 用户名
	 * @return 用户对象信息
	 */
	UserDTO getByUserName(String userName);
}
