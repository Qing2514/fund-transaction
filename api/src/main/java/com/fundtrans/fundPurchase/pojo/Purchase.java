package com.fundtrans.fundPurchase.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

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

    @NotNull
    private String user_id;

    @NotNull
    private String product_id;

    @NotNull
    private String card_id;

    private Date time;

    private BigDecimal amount;

    private BigDecimal count;

}
