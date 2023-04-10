package com.fund.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user_id;

    private String product_id;

    private String name;

    private String card_id;

    private BigDecimal value;

    private BigDecimal frozen_num;


}
