package com.fund.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author dyoung
 * @since 2022-05-07
 */
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String detail;

    /**
     * 净值
     */
    private BigDecimal networth;

    private String type;

    private String security;

    private BigDecimal prange;

}
