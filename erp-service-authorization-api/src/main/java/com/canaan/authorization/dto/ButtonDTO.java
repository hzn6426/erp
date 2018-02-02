package com.canaan.authorization.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @author zening
 * @date 2018年2月2日 下午2:22:46
 * @version 1.0.0
 */
@JsonInclude(Include.NON_NULL)
@Data @Accessors(chain = true) 
@ApiModel(value="按钮对象")
public class ButtonDTO implements Serializable {
	
	private static final long serialVersionUID = 8416179840325844378L;

	@ApiModelProperty("主键ID")
    private Integer id;
	
	@ApiModelProperty("按钮编码")
    private String buttonCode;
    
	@ApiModelProperty("按钮名称")
    private String buttonName;
    
	@ApiModelProperty("按钮功能连接")
    private String buttonUrl;
	
	@ApiModelProperty("菜单id")
    private Integer menuId;
}
