package com.canaan.authorization.service;


import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.authorization.api.SysButtonService;
import com.canaan.authorization.dto.ButtonDTO;
import com.canaan.authorization.entity.SysButton;
import com.canaan.authorization.mapper.SysButtonMapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;

public class SysButtonServiceImpl extends MBaseServiceImpl<SysButtonMapper, SysButton, ButtonDTO> implements SysButtonService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysButton> condition(ButtonDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<SysButton> primaryKeyCondition(ButtonDTO v) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
