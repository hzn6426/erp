package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.RoleDTO;
import com.canaan.authorization.dto.UserRoleDTO;
import com.canaan.common.MBaseService;
/**
 * 用户角色服务
 * @author zening
 * @date 2018年2月6日 上午11:09:45
 * @version 1.0.0
 */
public interface SysUserRoleService extends MBaseService<UserRoleDTO> {

	/**
	 * 根据用户名获取角色列表
	 * @param userName
	 * @return 用户角色列表，或空列表
	 */
	List<RoleDTO> listRolesByUserName(String userName);
	/**
	 * 根据用户id删除用户的角色
	 * @param userId
	 * @return 删除的记录条数，<code>userId</code>为null时抛出 {@link IllegalArgumentException} 
	 */
	int deleteRolesByUserId(Integer userId);
}
