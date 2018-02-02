package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 角色按钮权限对象
 * @author zening
 * @date 2018年2月2日 下午3:14:10
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="角色按钮对象")
public class RoleButtonDTO implements Serializable {

	private static final long serialVersionUID = 6318381430626714983L;
	
	@ApiModelProperty("主键ID")
	private Integer id;
	
	@ApiModelProperty("角色ID")
    private Integer roleId;
    
	@ApiModelProperty("按钮ID")
    private Integer buttonId;
}
