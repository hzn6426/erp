package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.MenuDTO;
import com.canaan.common.MBaseService;
/**
 * 菜单接口
 * @author zening
 * @date 2018年2月7日 上午10:52:49
 * @version 1.0.0
 */
public interface SysMenuService extends MBaseService<MenuDTO> {

	/**
	 * 根据id批量删除菜单
	 * @param idList 菜单id列表
	 */
	void deleteByIdList(List<Integer> idList);
	
}
