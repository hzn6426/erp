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
@TableName("sys_user")
public class SysUser extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @TableField("user_passwd")
    private String userPasswd;
    /**
     * 手机
     */
    @TableField("user_mobile")
    private String userMobile;
    /**
     * 真实姓名
     */
    @TableField("user_real_name")
    private String userRealName;


}
