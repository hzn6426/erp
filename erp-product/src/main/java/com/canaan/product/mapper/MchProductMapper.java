package com.canaan.product.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.canaan.product.dto.ProductDTO;
import com.canaan.product.entity.MchProduct;

/**
 * 商品mapper
 * @author zening
 * @date 2018年1月5日 下午3:01:29
 * @version V1.0
 */
public interface MchProductMapper extends BaseMapper<MchProduct> {

	List<ProductDTO> doSelect4Test();
}
