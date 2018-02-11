package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.dto.RoleButtonDTO;
import com.canaan.common.MBaseService;
/**
 * 角色按钮服务，提供角色按钮权限相关服务
 * @author zening
 * @date 2018年2月6日 上午11:44:55
 * @version 1.0.0
 */
public interface SysRoleButtonService extends MBaseService<RoleButtonDTO> {

	
	/**
	 * 根据角色id列表获取对应的按钮权限列表
	 * @param roleIdList 角色id列表
	 * @return 空或按钮全系列表
	 */
	List<ButtonDTO> listButtonsByRoleIdList(List<Integer> roleIdList);
	
	/**
	 * 根据角色id删除对应的按钮权限信息
	 * @param roleId 角色id
	 * @return 删除的行数，<code>roleId</code>为null时抛出 {@link IllegalArgumentException}
	 */
	void deleteButtonsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id删除对应id列表的按钮权限
	 * @param roleId 角色id
	 * @param idList 按钮id列表
	 */
	void deleteButtonsByRoleIdAndButtonIdList(Integer roleId, List<Integer> idList);
	
	/**
	 * 根据按钮角色id添加对应id列表的按钮权限
	 * @param roleId 角色id
	 * @param idList 按钮id列表
	 */
	void addButtonsByRoleIdAndButtonIdList(Integer roleId, List<Integer> idList);
	
}
