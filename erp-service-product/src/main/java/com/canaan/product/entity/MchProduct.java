package com.canaan.product.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.canaan.core.common.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author null123
 * @since 2018-01-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mch_product")
public class MchProduct extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("product_code")
    private String productCode;
    @TableField("product_name")
    private String productName;
    @TableField("product_url")
    private String productUrl;
    @TableField("brand_name")
    private String brandName;
    @TableField("type_name")
    private String typeName;
    @TableField("price")
    private Double price;
    @TableField("costPrice")
    private Double costPrice;
    @TableField("state")
    private String state;


}
