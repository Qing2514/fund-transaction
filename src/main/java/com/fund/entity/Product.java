package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "理财产品")
@TableName("product")
@Data
public class Product {

    @ApiModelProperty(value = "产品代码")
    @TableField(value = "id")
    @TableId
    private String id;

    @ApiModelProperty(value = "产品名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "产品类型")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "风险等级，1安全性最高，4风险性最高")
    @TableField(value = "security")
    private Integer security;

    @ApiModelProperty(value = "产品最近一天的单位净值")
    @TableField(value = "net_worth")
    private BigDecimal netWorth;

    @ApiModelProperty(value = "日增长率")
    @TableField(value = "growth")
    private BigDecimal growth;

    @ApiModelProperty(value = "产品经理")
    @TableField(value = "manager")
    private String manager;

    @ApiModelProperty(value = "产品状态，0-正常，1-已删除")
    @TableField(value = "state")
    private Integer state;

}
