package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(value = "份额")
@TableName("share")
@Data
@AllArgsConstructor
public class Share {

    @ApiModelProperty(value = "客户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "客户名称")
    @TableField(value = "user_name")
    private String userName;

    @ApiModelProperty(value = "产品id")
    @TableField(value = "product_id")
    private String productId;

    @ApiModelProperty(value = "产品名称")
    @TableField(value = "product_name")
    private String productName;

    @ApiModelProperty(value = "份额")
    @TableField(value = "share")
    private BigDecimal share;

}
