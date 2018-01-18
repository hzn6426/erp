package com.canaan.product.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.canaan.product.dto.ProductDTO;
import com.canaan.product.entity.MchProduct;
import com.jarvis.cache.annotation.Cache;

/**
 * 商品mapper
 * @author zening
 * @date 2018年1月5日 下午3:01:29
 * @version V1.0
 */
public interface MchProductMapper extends BaseMapper<MchProduct> {

	@Cache(expire = 300, key = "doSelect4Test")
	List<ProductDTO> doSelect4Test();
	
	
}
