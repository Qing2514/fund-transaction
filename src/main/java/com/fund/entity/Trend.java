package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "产品走势")
@TableName("trend")
@Data
@AllArgsConstructor
public class Trend {

    @ApiModelProperty(value = "日期")
    @TableField(value = "date")
    private Date date;

    @ApiModelProperty(value = "所属产品id")
    @TableField(value = "product_id")
    private String productId;

    @ApiModelProperty(value = "净值")
    @TableField(value = "price")
    private BigDecimal price;

}
