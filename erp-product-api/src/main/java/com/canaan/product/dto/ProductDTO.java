package com.canaan.product.dto;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.canaan.common.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@TableName("mch_product")
@ApiModel(value="商品对象")
public class ProductDTO extends BaseModel {
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
