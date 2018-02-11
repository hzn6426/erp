package com.canaan.privilege.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
//@JsonInclude(Include.NON_NULL)
@Data 
@ApiModel(value="用户对象")
public class UserDTO implements Serializable  {
	private static final long serialVersionUID = -8133164815820007379L;
	
	@ApiModelProperty(value="主键ID")
	private Integer id;
	@ApiModelProperty(value="名称")
	@NotBlank(message="{invalid_blank}")
	private String name;
	@ApiModelProperty(value="出生日期")
	@Past
	private Date birth;
	@ApiModelProperty(value="地址")
	private String address;
	@ApiModelProperty(value="电话")
	@Size(max=11, min=10, message="{invalid_length}")
	private String tel;
	@ApiModelProperty(value="密码")
	private String password;
	
}
