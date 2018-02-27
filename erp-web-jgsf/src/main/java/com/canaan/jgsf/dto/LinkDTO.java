package com.canaan.jgsf.dto;

import java.io.Serializable;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
@Data @Accessors(chain = true) 
@ApiModel(value="链接对象")
public class LinkDTO implements Serializable{

	private static final long serialVersionUID = -5397728879274660054L;
	
	@ApiModelProperty("请求链接")
	private Set<String> urlSet;
	@ApiModelProperty("请求方法的方式")
	private String method;
	@ApiModelProperty("链接的类方法")
	private String classMethod;
}
