package com.hundsun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String detail;

    /**
     * 起售份额
     */
    private Integer count;

    /**
     * 净值
     */
    private BigDecimal networth;


}
