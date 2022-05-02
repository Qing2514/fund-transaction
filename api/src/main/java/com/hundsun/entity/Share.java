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
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String productId;

    private BigDecimal num;


}
