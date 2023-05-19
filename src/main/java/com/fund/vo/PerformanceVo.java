package com.fund.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(value = "产品历史业绩")
@Data
@AllArgsConstructor
public class PerformanceVo {

    @NotBlank(message = "时间区间不能为空")
    private String time;

    @NotNull(message = "涨跌幅不能为空")
    private BigDecimal growth;

}
