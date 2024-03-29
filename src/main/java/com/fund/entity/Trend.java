package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "产品走势")
@Data
@AllArgsConstructor
public class Trend {

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @TableField(value = "date")
    @TableId
    private Date date;

    @ApiModelProperty(value = "净值")
    @TableField(value = "net_worth")
    private BigDecimal netWorth;

    @ApiModelProperty(value = "日增长率")
    @TableField(value = "growth")
    private BigDecimal growth;

}
