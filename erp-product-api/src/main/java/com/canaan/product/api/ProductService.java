package com.canaan.product.api;

import java.util.List;

import com.canaan.common.MBaseService;
import com.canaan.product.dto.ProductDTO;

public interface ProductService extends MBaseService<ProductDTO> {

	List<ProductDTO> listProducts();
}
