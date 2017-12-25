package com.canaan.jgsf.vo;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(Include.NON_NULL)
@Data @NoArgsConstructor
@ApiModel(value="用户对象")
public class UserVO {
	@ApiModelProperty(value="id")
	private String id;
	@ApiModelProperty(value="名称")
	@NotBlank(message="{invalid_blank}")
	private String name;
	@ApiModelProperty(value="出生日期")
	private Date birth;
	@ApiModelProperty(value="地址")
	private String address;
	@ApiModelProperty(value="电话")
	@Size(max=11, min=10, message="{invalid_length}")
	private String tel;
	
}
