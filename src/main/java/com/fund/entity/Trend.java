package com.fund.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trend implements Serializable {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date id;

    private String product_id;

    private BigDecimal price;//净值

    private String name;
}
