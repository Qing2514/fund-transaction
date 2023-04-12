package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "新增银行卡校验参数")
@Data
public class CardVo {

    @NotNull(message = "银行卡号不能为空")
    private String cardId;

    @NotNull(message = "用户id不能为空")
    private String userId;

    @NotNull(message = "银行卡密码不能为空")
    private String password;

}
