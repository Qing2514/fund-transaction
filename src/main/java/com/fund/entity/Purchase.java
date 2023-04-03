package com.fund.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author dyoung
 * @since 2022-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String user_id;

    private String user_name;

    private String product_id;

    private String product_name;

    private String card_id;

    private Date time;

    private BigDecimal amount;

    private BigDecimal count;

}
