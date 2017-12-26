package com.canaan.privilege.service;

import java.util.List;

import javax.annotation.Resource;

import org.jooq.impl.DSL;

import com.canaan.privilege.api.SysPrivilegeService;
import com.canaan.privilege.dto.PrivilegeDTO;
import static com.canaan.privilege.db.tables.SysPrivilege.SYS_PRIVILEGE;
import static com.canaan.privilege.db.tables.SysRolePrivilege.SYS_ROLE_PRIVILEGE;
import static com.canaan.privilege.db.tables.SysRole.SYS_ROLE;
import static com.canaan.privilege.db.tables.SysUserRole.SYS_USER_ROLE;
public class SysPrivilegeServiceImpl implements SysPrivilegeService{

	@Resource
	private DSL dsl;
	
	@Override
	public List<PrivilegeDTO> listPrivilegesByUserId(Integer userId) {
		return DSL.select(SYS_PRIVILEGE.PRIVILEGE_CODE.as("permission"), SYS_USER_ROLE.USER_ID, SYS_ROLE.ROLE_CODE.as("role"))
			.from(SYS_PRIVILEGE)
			.leftJoin(SYS_ROLE_PRIVILEGE).on(SYS_PRIVILEGE.ID.eq(SYS_ROLE_PRIVILEGE.PRIVILEGE_ID))
			.leftJoin(SYS_ROLE).on(SYS_ROLE.ID.eq(SYS_ROLE_PRIVILEGE.ROLE_ID))
			.leftJoin(SYS_USER_ROLE).on(SYS_USER_ROLE.ROLE_ID.eq(SYS_ROLE.ID))
			.where(SYS_USER_ROLE.USER_ID.eq(userId)).fetchInto(PrivilegeDTO.class);
	}


}
