package com.canaan.core.common;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

import lombok.Data;
@Data
public class BaseModel implements Serializable {
	private static final long serialVersionUID = 3106470533624582061L;
	
	@TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
    @TableField("create_id")
    private Integer createId;
    @TableField("update_id")
    private Integer updateId;
    @TableField("filter_sql")
    private String filterSql;
}
