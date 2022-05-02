package com.hundsun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Trend implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date id;

    private String productId;

    private BigDecimal price;


}
