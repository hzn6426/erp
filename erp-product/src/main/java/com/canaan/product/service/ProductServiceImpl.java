package com.canaan.product.service;


import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.product.api.ProductService;
import com.canaan.product.dto.ProductDTO;
import com.canaan.product.mapper.ProductMapper;

public class ProductServiceImpl extends MBaseServiceImpl<ProductMapper, ProductDTO> implements ProductService {

	@Override
	protected Columns select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<ProductDTO> condition(ProductDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected OrderBy orderby() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Wrapper<ProductDTO> primaryKeyCondition(ProductDTO e) {
		return new EntityWrapper<ProductDTO>().eq("id", e.getId());
	}

	

}
