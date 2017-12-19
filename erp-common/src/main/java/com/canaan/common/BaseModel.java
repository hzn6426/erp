package com.canaan.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter @JsonIgnoreType
@ApiModel(value="父对象")
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 3106470533624582061L;
	@ApiModelProperty(value = "通用ID")
	private Integer id;
	@ApiModelProperty(value = "创建时间")
	private Long createTime;
	@ApiModelProperty(value = "更新时间")
	private Long updateTime;
	@ApiModelProperty(value = "创建人ID")
	private Integer createId;
	@ApiModelProperty(value = "更新人ID")
	private Integer updateId;
	@ApiModelProperty(value = "过滤SQL")
	private String filterSql;
}
