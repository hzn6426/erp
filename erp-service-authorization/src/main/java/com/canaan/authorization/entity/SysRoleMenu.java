package com.canaan.authorization.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;


}
