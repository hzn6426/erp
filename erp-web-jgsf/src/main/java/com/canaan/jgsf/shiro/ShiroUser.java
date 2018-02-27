package com.canaan.jgsf.shiro;


import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author zening
 * @date 2018年2月23日 上午11:43:42
 * @version 1.0.0
 */
@Data @Accessors(chain = true)
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer id;          // 主键ID
    public String userName;      // 账号
    public String realName;         // 姓名
    public Integer deptId;      // 部门id
    public String deptName;        // 部门名称



}

