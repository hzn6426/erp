package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 角色对象
 * @author zening
 * @date 2018年2月2日 下午2:50:28
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="角色对象")
public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 6946021037724858653L;
	
	@ApiModelProperty("主键ID")
    private Integer id;
   
	@ApiModelProperty("角色编码")
    private String roleCode;
    
	@ApiModelProperty("角色名称")
    private String roleName;
    
	@ApiModelProperty("角色备注")
    private String note;

}
