package com.canaan.authorization.mapper;

import com.canaan.authorization.entity.SysButton;
import com.canaan.authorization.entity.SysRoleButton;

import java.util.List;
import java.util.Map;

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
	 * 根据角色id列表获取对应的按钮列表
	 * @param roleIdList 角色id列表
	 * @return 查询的按钮列表，如果没有数据，则为空列表
	 */
	List<SysButton> listButtonByRoleIdList(List<Integer> roleIdList);
	
	/**
	 * 删除角色对应的按钮关联
	 * @param roleId 角色id
	 * @return 删除的记录条数
	 */
	int deleteByRoleId(@Param("roleId") int roleId);
	
	/**
	 * 删除角色id对应的按钮id列表在<code>idList</code>中的按钮权限
	 * @param roleId
	 * @param idList
	 * @return
	 */
	int deleteByMapRoleIdAndButtonIds(Map<String, Object> map);
	
}
