package com.fundtrans.fundPurchase.service;

import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.Date;

@CloudService
public interface PurchaseService {
    RespBean doPurchase(Date date);
    RespBean updatePurchaseByDate(Date datetime);
    RespBean getSum();
    RespBean findById(int id);

    RespBean findByAll(TransSelectVo transSelectVo);
}
