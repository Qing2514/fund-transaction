package com.fundtrans.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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
public class Ptrans implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull
    private String user_id;

    @NotNull
    private String product_id;

    @NotNull
    private String card_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String method;

    @NotNull
    private BigDecimal amount;

    /**
     * 0表示未处理 1表示已处理 2表示已撤回
     */
    private Integer state;


}
