package com.canaan.privilege.dto;

import com.canaan.common.BaseModel;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class PrivilegeDTO extends BaseModel {
	private static final long serialVersionUID = 3664138339949625927L;
	
	private String permission;
	private Integer userId;
	private String role;
}
