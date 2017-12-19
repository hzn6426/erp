package com.canaan.privilege.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@JsonInclude(Include.NON_NULL)
@Setter @Getter
@ApiModel(value="菜单对象")
public class MenuVO {
	@ApiModelProperty(value = "ID")
	private Integer id;
	@ApiModelProperty(value = "编码")
    private String  code;
	@ApiModelProperty(value = "名称")
    private String  name;
	@ApiModelProperty(value = "菜单链接")
    private String  url;
	@ApiModelProperty(value = "父菜单")
    private Integer parentId;
	
}

