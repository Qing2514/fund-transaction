package com.fund.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String user_id;

    private String product_id;

    private String card_id;

    private String num;

    private Date time;


}
