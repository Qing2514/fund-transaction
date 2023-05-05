package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(value = "银行卡")
@TableName("card")
@Data
public class Card {

    @ApiModelProperty(value = "银行卡号")
    @TableField(value = "card_id")
    @TableId
    private String cardId;

    @ApiModelProperty(value = "客户id")
    @TableField(value = "user_id")
    private String userId;

    @ApiModelProperty(value = "客户名称")
    @TableField(value = "user_name")
    private String userName;

    @ApiModelProperty(value = "银行卡余额")
    @TableField(value = "amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "银行卡密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "银行卡状态，0-正常，1-注销")
    @TableField(value = "state")
    private Integer state;

}