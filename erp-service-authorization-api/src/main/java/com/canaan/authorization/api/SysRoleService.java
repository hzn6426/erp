package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.MenuButtonTreeDTO;
import com.canaan.authorization.dto.RoleDTO;
import com.canaan.common.MBaseService;
/**
 * 角色接口
 * @author zening
 * @date 2018年2月7日 上午10:49:04
 * @version 1.0.0
 */
public interface SysRoleService extends MBaseService<RoleDTO> {
	
	/**
	 * 根据菜单按钮树保存角色的权限
	 * @param menuButtonTreeIdList 菜单按钮树id列表
	 * @param roleId 角色id
	 */
	void saveRolePerms(List<String> menuButtonTreeIdList, Integer roleId);
	
	/**
	 * 根据角色列表及菜单父节点该节点对应的按钮或菜单树，如果角色列表拥有权限，则checked属性为true,<code>nodeParentId</code>为null或空时获取所有一级菜单信息
	 * @param roleIdList 角色id列表
	 * @param nodeParentId 父菜单id
	 * @return 菜单按钮树
	 */
	List<MenuButtonTreeDTO> listLazyMenusOrButtonsByRoleIdList(List<Integer> roleIdList, String nodeParentId);
}
