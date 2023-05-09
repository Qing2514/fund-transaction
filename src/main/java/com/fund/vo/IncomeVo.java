package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "收益对比")
@Data
@AllArgsConstructor
public class IncomeVo {

    @NotNull(message = "总申购金额不能为空")
    private BigDecimal amount;

    @NotNull(message = "一次性购入收益不能为空")
    private BigDecimal purchaseIncome;

    @NotNull(message = "定投收益不能为空")
    private BigDecimal investmentIncome;

}
