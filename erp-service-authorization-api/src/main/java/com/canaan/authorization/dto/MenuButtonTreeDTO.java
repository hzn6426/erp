package com.canaan.authorization.dto;

import java.io.Serializable;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data @Accessors(chain = true) 
@ApiModel(value="菜单按钮树对象")
public class MenuButtonTreeDTO implements Serializable {
	private static final long serialVersionUID = 1895911379561118102L;

	@ApiModelProperty("菜单或按钮id,菜单节点:前缀为menu,按钮节点:前缀btn")
	private String nodeId;
	
	@ApiModelProperty("节点名称,菜单名称或按钮名称")
	private String nodeName;
	
	@ApiModelProperty("节点类型,menu为菜单,btn为按钮")
	private String nodeType; 
	
	@ApiModelProperty("是否选中,选中为角色拥有权限")
	private boolean checked;
	
	@ApiModelProperty("链接地址")
	private String url;
}
