package com.canaan.jgsf.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@ApiModel(value="菜单对象")
@JsonInclude(Include.NON_NULL)
@Setter @Getter
public class MenuVO {
	@ApiModelProperty(name = "id", value = "ID")
	private String id;
	@ApiModelProperty(name = "code", value = "编码")
    private String  code;
	@ApiModelProperty(value = "名称")
    private String  name;
	@ApiModelProperty(value = "菜单链接")
    private String  url;
	@ApiModelProperty(value = "父菜单")
    private String parentId;
	
	
	
}

