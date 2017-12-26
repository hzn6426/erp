package com.canaan.privilege.service;

import java.util.List;

import org.jooq.Condition;
import org.jooq.SelectField;
import org.jooq.SortField;

import com.canaan.core.service.BaseServiceImpl;
import com.canaan.privilege.api.SysUserService;
import com.canaan.privilege.db.tables.SysUser;
import com.canaan.privilege.db.tables.records.SysUserRecord;
import com.canaan.privilege.dto.UserDTO;
import com.google.common.collect.ImmutableList;
import static com.canaan.privilege.db.tables.SysUser.SYS_USER;
public class SysUserServiceImpl extends BaseServiceImpl<SysUserRecord, SysUser, UserDTO> implements SysUserService{

	@Override
	public Condition condition(UserDTO e) {
		return null;
	}

	@Override
	public List<SortField<?>> orderby(UserDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Condition primaryKeyCondition(UserDTO e) {
		return SysUser.SYS_USER.ID.eq(e.getId());
	}

	@Override
	public List<SelectField<?>> select() {
		ImmutableList<SelectField<?>> selects = ImmutableList.of(SYS_USER.ID,SYS_USER.USER_NAME,SYS_USER.USER_MOBILE,SYS_USER.USER_REAL_NAME);
		return selects;
	}

}
