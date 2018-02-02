package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 用户角色关联
 * @author zening
 * @date 2018年2月2日 下午3:04:56
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="用户角色对象")
public class UserRoleDTO implements Serializable {

	private static final long serialVersionUID = 9218102678164992615L;

	@ApiModelProperty("主键ID")
	private Integer id;
	
	@ApiModelProperty("用户ID")
    private Integer userId;
    
	@ApiModelProperty("角色ID")
    private Integer roleId;
}
