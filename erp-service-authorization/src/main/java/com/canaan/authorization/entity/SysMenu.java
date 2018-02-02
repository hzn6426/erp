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
@TableName("sys_menu")
public class SysMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单编码
     */
    @TableField("menu_code")
    private String menuCode;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单URL
     */
    @TableField("menu_url")
    private String menuUrl;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;


}
