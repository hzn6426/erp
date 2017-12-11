package com.canaan.jgsf.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
@JsonInclude(Include.NON_NULL)
@Builder @Getter
@ApiModel(description="用户对象")
public class UserVO {
	@ApiModelProperty(value="id号")
	private String id;
	@ApiModelProperty(value="名称")
	private String name;
	@ApiModelProperty(value="出生日期")
	private Date birth;
	@ApiModelProperty(value="地址")
	private String address;
	@ApiModelProperty(value="电话")
	private String tel;
	
}
