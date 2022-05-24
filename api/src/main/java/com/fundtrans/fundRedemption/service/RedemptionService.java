package com.fundtrans.fundRedemption.service;

import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.Date;

@CloudService
public interface RedemptionService {
    RespBean doRedemption(Date date);
    RespBean updateRedemptionByDate(Date datetime, String productId, BigDecimal amount);
}
