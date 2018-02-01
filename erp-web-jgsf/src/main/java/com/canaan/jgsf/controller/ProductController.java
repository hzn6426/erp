package com.canaan.jgsf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
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
import com.canaan.util.tool.LoopUtil;

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
		List<ProductDTO> list =  productService.listProducts();
		
		//刷选品牌名称(brandName)是可口可乐的对象
		//传统方式:
//		List<ProductDTO> targetList = new ArrayList<>();
//		for (ProductDTO dto : list) {
//			if ("可口可乐".equals(dto.getBrandName())) {
//				targetList.add(dto);
//			}
//		}
//		//predicate方式
//		Predicate<ProductDTO> brandPredicate = new Predicate<ProductDTO>() {
//			@Override
//			public boolean evaluate(ProductDTO dto) {
//				return "可口可乐".equals(dto.getBrandName());
//			}
//		};
//		targetList = (List<ProductDTO>)CollectionUtils.select(list, brandPredicate);
		//封装方式
//		targetList = LoopUtil.select(list, "brandName", "可口可乐");
		
		//将品牌名称(brandName)是可口可乐的对象删除(状态设置为DELETE)
		//1.传统方式
//		for (ProductDTO dto : list) {
//			if ("可口可乐".equals(dto.getBrandName())) {
//				dto.setState("DELETE");
//			}
//		}
//		//2 closure方式
//		Closure<ProductDTO> closure = new Closure<ProductDTO>() {
//			@Override
//			public void execute(ProductDTO dto) {
//				if ("可口可乐".equals(dto.getBrandName())) {
//					dto.setState("DELETE");
//				}
//				
//			}
//		};
//		IterableUtils.forEach(list, closure);
		//3 封装方式
		List<ProductDTO> targetList = LoopUtil.select(list, "brandName", "可口可乐");
		LoopUtil.changePropertyValue(targetList, "state", "DELETE");
		
//		Predicate<ProductDTO> predicate = new Predicate<ProductDTO>() {
//			
//			@Override
//			public boolean evaluate(ProductDTO dto) {
//				return "OOEK".equals(dto.getProductCode());
//			}
//		};
//		Map<String, Object> changePropertyValueMap = new HashMap<>();
//		changePropertyValueMap.put("productName", "1商品名称");
//		changePropertyValueMap.put("productCode", "1商品编码");
//		LoopUtil.changePropertyValue(list, predicate, changePropertyValueMap);
		return list;
	}
	

}
