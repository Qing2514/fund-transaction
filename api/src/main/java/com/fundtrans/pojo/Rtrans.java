package com.fundtrans.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Rtrans implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String user_id;

    private String user_name;

    private String product_id;

    private String product_name;

    private String card_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String method;

    private BigDecimal count;

    /**
     * 记录状态：0表示未处理 1表示已处理 2表示已撤回 3表示交易失败
     */
    private Integer state;


}
