package com.canaan.jgsf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.canaan.common.SearchResult;
import com.canaan.jgsf.common.MBaseController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.util.Assert;
import com.canaan.product.api.ProductService;
import com.canaan.product.dto.ProductDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(tags = "商品管理", description="商品的增删改查功能!")
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController extends MBaseController<ProductDTO> {

	@Resource
	private ProductService productService;
	
	@ApiOperation(value = "查询列表")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseResult<ProductDTO> list(@RequestParam int pageNumber, @RequestParam int pageSize) {
		ProductDTO product = new ProductDTO();
		SearchResult<ProductDTO> productResult = this.list(product, pageNumber, pageSize);
		return ResponseResult.build(productResult.getTotalSize(), productResult.getDataList());
	}
	
	@ApiOperation(value = "查询")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductDTO get(@PathVariable Long id) {
		Assert.checkArgument(id);
		return super.get(id);
	}
	
	@ApiOperation(value = "保存")
	@RequestMapping(method=RequestMethod.POST)
	public void save(@RequestBody ProductDTO product) {
		Assert.checkArgument(product);
		super.save(product);
	}
	
	@ApiOperation(value = "更新")
	@RequestMapping(method=RequestMethod.PUT)
	public void update(@RequestBody ProductDTO product) {
		Assert.checkArgument(product);
		super.update(product);
	}
	
	@ApiOperation(value = "删除")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		Assert.checkArgument(id);
		ProductDTO product = new ProductDTO();
		product.setId(id);
		super.delete(product);
	}
	
	@ApiOperation(value = "测试查询列表")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public List<ProductDTO> justforTest() {
		return productService.listProducts();
	}
}
