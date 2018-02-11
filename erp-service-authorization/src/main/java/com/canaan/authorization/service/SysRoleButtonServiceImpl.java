package com.canaan.authorization.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysRoleButtonService;
import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.dto.RoleButtonDTO;
import com.canaan.authorization.entity.SysButton;
import com.canaan.authorization.entity.SysRoleButton;
import com.canaan.authorization.mapper.SysRoleButtonMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.util.tool.Checker;

public class SysRoleButtonServiceImpl extends MBaseServiceImpl<SysRoleButtonMapper, SysRoleButton, RoleButtonDTO> 
	implements SysRoleButtonService {


	@Override
	protected Columns select() {
		return null;
	}

	@Override
	protected Wrapper<SysRoleButton> condition(RoleButtonDTO v) {
		return null;
	}

	@Override
	protected OrderBy orderby() {
		return null;
	}

	@Override
	protected Wrapper<SysRoleButton> primaryKeyCondition(RoleButtonDTO v) {
		return new EntityWrapper<SysRoleButton>().eq("id", v.getId());
	}

	@Override
	public List<ButtonDTO> listButtonsByRoleIdList(List<Integer> roleIdList) {
		List<ButtonDTO> list = new ArrayList<>();
		
		if (Checker.BeNotEmpty(roleIdList)) {
			List<SysButton> buttonList = this.baseMapper.listButtonByRoleIdList(roleIdList);
			if (Checker.BeNotEmpty(buttonList)) {
				list = new ArrayList<>(collectionMapper.mapCollection(buttonList, ButtonDTO.class));
			}
		}
		
		return list;
	}

	@Override
	public void deleteButtonsByRoleId(Integer roleId) {
		Assert.CheckArgument(roleId);
		this.baseMapper.deleteByRoleId(roleId);
	}

	@Override
	public void deleteButtonsByRoleIdAndButtonIdList(Integer roleId, List<Integer> idList) {
		Assert.CheckArgumentStrict(roleId, idList);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleId", roleId);
		paramMap.put("buttonIdList", idList);
		this.baseMapper.deleteByMapRoleIdAndButtonIds(paramMap);
		
	}

	@Override
	public void addButtonsByRoleIdAndButtonIdList(Integer roleId, List<Integer> idList) {
		Assert.CheckArgumentStrict(roleId, idList);
		List<SysRoleButton> list = new ArrayList<>(idList.size());
		for (Integer buttonId : idList) {
			list.add(new SysRoleButton().setButtonId(buttonId).setRoleId(roleId));
		}
		this.insertBatch(list);
	}

	

}
