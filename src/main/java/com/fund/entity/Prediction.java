package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "走势预测")
@TableName("prediction")
@Data
public class Prediction {

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "date")
    private Date date;

    @ApiModelProperty(value = "产品代码")
    @TableField(value = "product_id")
    private String productId;

    @ApiModelProperty(value = "净值")
    @TableField(value = "net_worth")
    private BigDecimal netWorth;

    @ApiModelProperty(value = "日增长率")
    @TableField(value = "growth")
    private BigDecimal growth;

}
