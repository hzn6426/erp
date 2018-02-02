package com.canaan.authorization.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.canaan.core.common.BaseModel;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 * @author zening
 * @date 2018年2月2日 下午2:15:55
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_button")
public class SysButton extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 按钮ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 按钮编码
     */
    @TableField("button_code")
    private String buttonCode;
    /**
     * 按钮名称
     */
    @TableField("button_name")
    private String buttonName;
    /**
     * 按钮URL
     */
    @TableField("button_url")
    private String buttonUrl;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;


}
