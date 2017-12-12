package com.canaan.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter @JsonIgnoreType
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 3106470533624582061L;
	
	private Integer id;
	private Long createTime;
	private Long updateTime;
	private Integer createId;
	private Integer updateId;
	private String filterSql;
}
