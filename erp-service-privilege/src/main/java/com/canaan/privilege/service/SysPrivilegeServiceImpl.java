package com.canaan.privilege.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.jooq.DSLContext;

import com.canaan.privilege.api.SysPrivilegeService;
import com.google.common.collect.Lists;

import static com.canaan.privilege.db.tables.SysUserRole.SYS_USER_ROLE;
import static com.canaan.privilege.db.tables.SysRoleButton.SYS_ROLE_BUTTON;
import static com.canaan.privilege.db.tables.SysRoleMenu.SYS_ROLE_MENU;
import static com.canaan.privilege.db.tables.SysButton.SYS_BUTTON;
import static com.canaan.privilege.db.tables.SysMenu.SYS_MENU;
public class SysPrivilegeServiceImpl implements SysPrivilegeService{

	@Resource
	private DSLContext dsl;
	
	@Override
	public List<String> listPermStringByUserId(Integer userId) {
		//查按钮权限
		List<String> buttons = dsl.select()
			.from(SYS_BUTTON)
			.leftJoin(SYS_ROLE_BUTTON).on(SYS_BUTTON.ID.eq(SYS_ROLE_BUTTON.BUTTON_ID))
			.leftJoin(SYS_USER_ROLE).on(SYS_ROLE_BUTTON.ROLE_ID.eq(SYS_USER_ROLE.ROLE_ID))
			.where(SYS_USER_ROLE.USER_ID.eq(userId)).fetch(SYS_BUTTON.BUTTON_CODE);
		//查菜单权限
		List<String> menus = dsl.select()
			.from(SYS_MENU)
			.leftJoin(SYS_ROLE_MENU).on(SYS_MENU.ID.eq(SYS_ROLE_MENU.MENU_ID))
			.leftJoin(SYS_USER_ROLE).on(SYS_ROLE_MENU.ROLE_ID.eq(SYS_USER_ROLE.ROLE_ID))
			.where(SYS_USER_ROLE.USER_ID.eq(userId)).fetch(SYS_MENU.MENU_CODE);
		List<String> perms = Lists.newArrayList(CollectionUtils.union(buttons, menus));
		return perms;
	}


}
