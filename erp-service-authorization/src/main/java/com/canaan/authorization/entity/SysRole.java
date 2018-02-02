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
@TableName("sys_role")
public class SysRole extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色备注
     */
    private String note;


}
