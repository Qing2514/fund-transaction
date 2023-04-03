package com.fund.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Qing2514
 */
@ApiModel(value = "用户")
@TableName("user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "证件类型+证件号码")
    @TableField(value = "id")
    @TableId
    private String id;

    @ApiModelProperty(value = "姓名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty(value = "类型")
    @TableField(value = "type")
    private String type;

    @ApiModelProperty(value = "证件类型")
    @TableField(value = "ctype")
    private String ctype;

    @ApiModelProperty(value = "证件号")
    @TableField(value = "cid")
    private String cid;

    @ApiModelProperty(value = "手机号")
    @TableField(value = "phone")
    private String phone;

    @ApiModelProperty(value = "年龄")
    @TableField(value = "age")
    private Integer age;

    @ApiModelProperty(value = "性别")
    @TableField(value = "sex")
    private String sex;

    @ApiModelProperty(value = "地址")
    @TableField(value = "address")
    private String address;

    @ApiModelProperty(value = "工作")
    @TableField(value = "job")
    private String job;

    @ApiModelProperty(value = "安全性")
    @TableField(value = "security")
    private String security;

    @ApiModelProperty(value = "状态")
    @TableField(value = "state")
    private Integer state;

}
