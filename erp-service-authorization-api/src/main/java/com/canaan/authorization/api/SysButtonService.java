package com.canaan.authorization.api;

import java.util.List;

import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.common.MBaseService;
/**
 * 按钮接口
 * @author zening
 * @date 2018年2月7日 上午10:53:07
 * @version 1.0.0
 */
public interface SysButtonService extends MBaseService<ButtonDTO> {

	/**
	 * 根据id批量删除按钮
	 * @param idList 按钮id列表
	 */
	void deleteByIdList(List<Integer> idList);
	
}
