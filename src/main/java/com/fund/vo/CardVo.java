package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "新增银行卡校验参数")
@Data
public class CardVo {

    @NotBlank(message = "银行卡号不能为空")
    private String cardId;

    @NotBlank(message = "用户id不能为空")
    private String userId;

    @NotBlank(message = "银行卡密码不能为空")
    private String password;

}
