package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单对象
 * @author zening
 * @date 2018年2月2日 下午2:43:02
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="菜单对象")
public class MenuDTO implements Serializable {
	private static final long serialVersionUID = 3458932289586503851L;
	
	@ApiModelProperty("主键ID")
	private Integer id;
    
	@ApiModelProperty("菜单编码")
    private String menuCode;
  
	@ApiModelProperty("菜单名称")
    private String menuName;
    
	@ApiModelProperty("菜单URL")
    private String menuUrl;
    
	@ApiModelProperty("父菜单ID")
    private Integer parentId;
	
	@ApiModelProperty("优先级")
	private Short priority;

}
