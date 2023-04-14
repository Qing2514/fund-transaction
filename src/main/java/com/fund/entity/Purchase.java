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

@ApiModel(value = "申购记录")
@TableName("purchase")
@Data
public class Purchase {

    @ApiModelProperty(value = "订单id")
    @TableField(value = "id")
    @TableId
    private String id;

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

    @ApiModelProperty(value = "银行卡id")
    @TableField(value = "card_id")
    private String cardId;

    @ApiModelProperty(value = "申购时间")
    @TableField(value = "datetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date datetime;

    @ApiModelProperty(value = "申购金额")
    @TableField(value = "amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "获得份额")
    @TableField(value = "share")
    private BigDecimal share;

    @ApiModelProperty(value = "订单状态，0-进行中，1-已完成，2-已取消")
    @TableField(value = "state")
    private Integer state;

}
