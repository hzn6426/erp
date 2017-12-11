package com.canaan.privilege.service;


import org.jooq.Condition;
import org.jooq.SortField;

import com.canaan.privilege.api.SysMenuService;
import com.canaan.privilege.db.tables.SysMenu;
import com.canaan.privilege.db.tables.records.SysMenuRecord;
import com.canaan.privilege.dto.MenuDTO;

public class DefaultMenuService extends DefaultBaseService<SysMenuRecord, SysMenu, MenuDTO> implements SysMenuService {

	@Override
	public Condition condition(MenuDTO e) {
		return null;
	}

	@Override
	public SortField<?> orderby(MenuDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Condition primaryKeyCondition(MenuDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
