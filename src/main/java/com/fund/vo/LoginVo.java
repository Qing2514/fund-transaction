package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "登录校验参数")
@Data
public class LoginVo {

    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须是11位")
    private String phone;

    @NotBlank(message = "账户密码不能为空")
    @Size(min = 6, max = 16, message = "密码必须是6到16位")
    private String password;

}
