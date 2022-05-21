package com.fundtrans.fundRedemption.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = false)
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user_id;

    private String product_id;

    private String card_id;

    private BigDecimal num;

    private BigDecimal frozen_num;


}
