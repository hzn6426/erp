package com.canaan.privilege.dto;

import com.canaan.common.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
@Builder @Getter @ToString
@ApiModel(value="权限对象")
public class PrivilegeDTO extends BaseModel {
	private static final long serialVersionUID = 3664138339949625927L;
	@ApiModelProperty("全线字符串")
	private String permission;
	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("角色")
	private String role;
	
	
}
