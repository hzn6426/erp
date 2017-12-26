package com.canaan.privilege.service;


import java.util.List;

import org.jooq.Condition;
import org.jooq.SelectField;
import org.jooq.SortField;
import org.jooq.impl.DSL;

import com.canaan.common.SearchResult;
import com.canaan.core.exception.ServerException;
import com.canaan.core.service.BaseServiceImpl;
import com.canaan.privilege.api.SysMenuService;
import com.canaan.privilege.common.PrivilegeExceptionEnum;
import com.canaan.privilege.db.tables.SysMenu;
import com.canaan.privilege.db.tables.records.SysMenuRecord;
import com.canaan.privilege.dto.MenuDTO;
import com.google.common.collect.ImmutableList;

import static com.canaan.privilege.db.tables.SysMenu.SYS_MENU;
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuRecord, SysMenu, MenuDTO> implements SysMenuService {

	@Override
	public Condition condition(MenuDTO e) {
		return DSL.trueCondition();
	}

	@Override
	public List<SelectField<?>> select() {
		ImmutableList<SelectField<?>> selects = ImmutableList.of(SYS_MENU.ID,SYS_MENU.MENU_NAME,SYS_MENU.MENU_CODE,SYS_MENU.MENU_URL);
		return selects;
	}

	@Override
	public SearchResult<MenuDTO> list(MenuDTO e, int pageNumber, int pageSize) {
//		int offset =  (pageNumber-1) * pageSize; 
//		List<MenuDTO> dataList = dsl.select(SYS_MENU.ID,SYS_MENU.NAME,SYS_MENU.CODE,SYS_MENU.URL)
//			.from(SYS_MENU).where(condition(e))
//			.orderBy(orderby(e))
//			.limit(pageSize)
//			.offset(offset)
//			.fetchInto(MenuDTO.class);
//		int count = dsl.selectCount().from(SYS_MENU).where(condition(e)).fetchOne(0, Integer.class);
//		SearchResult<MenuDTO> result = new SearchResult<>(count,dataList);
//		return result;
		throw new ServerException(PrivilegeExceptionEnum.EXCEPTION_SAMPLE);
//		return super.list(e, pageNumber, pageSize);
	}

	@Override
	public List<SortField<?>> orderby(MenuDTO e) {
		ImmutableList<SortField<?>> orderbys = ImmutableList.of(SysMenu.SYS_MENU.ID.asc(),SysMenu.SYS_MENU.MENU_NAME.desc());
		return orderbys;
	}

	@Override
	public Condition primaryKeyCondition(MenuDTO e) {
		return SysMenu.SYS_MENU.ID.eq(e.getId());
	}

	
	

}
