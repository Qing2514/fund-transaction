package com.fundtrans.fundRedemption.service;

import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.Date;

@CloudService
public interface RedemptionService {
    RespBean doRedemption(Date date);
    RespBean updateRedemptionByDate(Date datetime, String productId, BigDecimal amount);
    RespBean getSum();
    RespBean findByDate(Datetime datetime);
    RespBean findByUserId(String user_id);
    RespBean findByDateAndUserId(String user_id, Date date1, Date date2);

    RespBean findById(int id);
}
