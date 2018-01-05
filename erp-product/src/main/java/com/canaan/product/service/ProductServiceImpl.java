package com.canaan.product.service;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.canaan.core.batisplus.OrderBy;
import com.canaan.core.service.MBaseServiceImpl;
import com.canaan.core.util.Assert;
import com.canaan.product.api.ProductService;
import com.canaan.product.dto.ProductDTO;
import com.canaan.product.entity.MchProduct;
import com.canaan.product.mapper.MchProductMapper;

public class ProductServiceImpl extends MBaseServiceImpl<MchProductMapper, MchProduct, ProductDTO> implements ProductService {

	
	
	@Override
	protected Columns select() {
		//just select some column
		return Columns.create().column("product_code").column("product_name");
	}

	@Override
	protected Wrapper<MchProduct> condition(ProductDTO e) {
		Assert.checkArgument(e);
		
		EntityWrapper<MchProduct> wrapper = new EntityWrapper<>();
		wrapper.eq(StringUtils.isNoneBlank(e.getProductCode()), "product_code", e.getProductCode())
			.eq(StringUtils.isNoneBlank(e.getProductName()), "product_name", e.getProductName());
		return wrapper;
	}

	@Override
	protected OrderBy orderby() {
		return new OrderBy().asc("id");
	}

	@Override
	protected Wrapper<MchProduct> primaryKeyCondition(ProductDTO e) {
		return new EntityWrapper<MchProduct>().eq("id", e.getId());
	}

	@Override
	public List<ProductDTO> listProducts() {
		return this.baseMapper.doSelect4Test();
	}

	
	
	

}
