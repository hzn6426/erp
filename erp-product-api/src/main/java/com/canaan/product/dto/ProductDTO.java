package com.canaan.product.dto;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@JsonInclude(Include.NON_NULL)
@Getter @Setter 
@ApiModel(value="商品对象")
public class ProductDTO implements Serializable  {
	private static final long serialVersionUID = 1863419141498676171L;

	@TableId
	@ApiModelProperty("主键ID")
	private Long id;
	
	@ApiModelProperty("产品编码")
	private String productCode;
	
	@ApiModelProperty("产品名称")
	private String productName;
	
	@ApiModelProperty("产品图片URL")
	private String productUrl;
	
	@ApiModelProperty("品牌名称")
	private String brandName;
	
	@ApiModelProperty("分类名称")
	private String typeName;
	
}
