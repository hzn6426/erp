package com.canaan.authorization.mapper;

import com.canaan.authorization.entity.SysRole;
import com.canaan.authorization.entity.SysUserRole;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 用户角色映射
 * @author zening
 * @date 2018年2月5日 下午1:48:38
 * @version 1.0.0
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	/**
	 * 删除用户id为<code>userId</code>的角色信息
	 * @param userId
	 * @return
	 */
	int deleteByUserId(@Param("userId") Integer userId);
	
	/**
	 * 根据用户名列出用户所有角色列表
	 * @param userName
	 * @return
	 */
	List<SysRole> listRoleByUserName(@Param("userName") String userName);
}
