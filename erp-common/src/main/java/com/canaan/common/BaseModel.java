package com.canaan.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter @JsonIgnoreType
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 3106470533624582061L;
	@ApiModelProperty(value = "通用ID")
	private Integer id;
	@ApiModelProperty(value = "创建时间", hidden=true)
	private Long createTime;
	@ApiModelProperty(value = "更新时间", hidden=true)
	private Long updateTime;
	@ApiModelProperty(value = "创建人ID", hidden=true)
	private Integer createId;
	@ApiModelProperty(value = "更新人ID", hidden=true)
	private Integer updateId;
	@ApiModelProperty(value = "过滤SQL", hidden=true)
	private String filterSql;
}
