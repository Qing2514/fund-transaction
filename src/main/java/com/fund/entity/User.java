package com.fund.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户")
@TableName("user")
@Data
public class User {

    @ApiModelProperty(value = "证件号码")
    @TableField(value = "cid")
    @TableId
    private String cid;

    @ApiModelProperty(value = "证件类型，0-居民身份证，1-护照")
    @TableField(value = "ctype")
    private Integer ctype;

    @ApiModelProperty(value = "客户名称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "银行卡密码", required = true)
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "客户类型，0-机构，1-个人")
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty(value = "风险等级，1为安全性最高，4风险性最高")
    @TableField(value = "security")
    private Integer security;

    @ApiModelProperty(value = "账户状态，0-正常，1-已销户")
    @TableField(value = "state")
    private Integer state = 0;

}
