package com.fundtrans.userManage.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CardVo {
    @NotNull
    private String card_id;

    @NotNull
    private String user_id;

    private BigDecimal account;
}
