package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "新增申购记录校验参数")
@Data
public class RedemptionVo {

    @NotBlank(message = "客户id不能为空")
    private String userId;

    @NotBlank(message = "产品id不能为空")
    private String productId;

    @NotBlank(message = "银行卡号不能为空")
    private String cardId;

    @NotNull(message = "赎回份额不能为空")
    private BigDecimal share;

}
