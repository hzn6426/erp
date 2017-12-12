package com.canaan.privilege.dto;

import com.canaan.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter @JsonInclude(Include.NON_NULL)
public class MenuDTO extends BaseModel{
	private static final long serialVersionUID = -2913587203203194413L;
	private Integer id;
    private String  code;
    private String  name;
    private String  url;
    private Integer parentId;
}
