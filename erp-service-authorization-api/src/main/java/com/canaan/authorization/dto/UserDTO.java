package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 用户对象
 * @author zening
 * @date 2018年2月2日 下午2:59:00
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="用户对象")
public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = -1890099483263564634L;

	@ApiModelProperty("主键ID")
    private Integer id;
    
	@ApiModelProperty("用户名")
    private String userName;
    
	@ApiModelProperty("密码")
    private String userPasswd;
    
	@ApiModelProperty("手机")
    private String userMobile;

	@ApiModelProperty("真实姓名")
    private String userRealName;
	
	private String state;
}
