package com.fundtrans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fundtrans.pojo.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TransSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String user_id;

    private String product_id;

    private String card_id;

    private int state = 3;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date date1;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date date2;
}
