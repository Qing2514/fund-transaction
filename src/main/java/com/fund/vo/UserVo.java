package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(value = "新增用户校验参数")
@Data
public class UserVo {

    @NotBlank(message = "证件号码不能为空")
    private String cid;

    @NotNull(message = "证件类型不能为空")
    private Integer ctype;

    @NotBlank(message = "客户姓名不能为空")
    private String name;

    @NotNull(message = "客户类型不能为空")
    private Integer type;

    @NotBlank(message = "账户密码不能为空")
    @Size(min = 6, max = 16, message = "密码必须是6到16位")
    private String password;

    @NotBlank(message = "手机号不能为空")
    @Size(min = 11, max = 11, message = "手机号必须是11位")
    private String phone;

}
