package com.fund.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String card_id;

    private String user_id;

    private BigDecimal account;


}