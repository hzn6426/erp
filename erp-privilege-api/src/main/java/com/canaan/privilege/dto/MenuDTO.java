package com.canaan.privilege.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter @JsonInclude(Include.NON_NULL) 
@ApiModel(value="菜单对象")
public class MenuDTO implements Serializable {
	private static final long serialVersionUID = -2913587203203194413L;
	
	@ApiModelProperty(value = "主键ID")
	private Integer id;
	@ApiModelProperty(value = "编码")
    private String  menuCode;
	@ApiModelProperty(value = "名称")
    private String  menuName;
	@ApiModelProperty(value = "菜单链接")
    private String  menuUrl;
	@ApiModelProperty(value = "父菜单")
    private Integer parentId;
	
	
}
