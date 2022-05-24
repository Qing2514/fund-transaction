package com.fundtrans.clearing.service;

import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;

@CloudService
public interface ClearingService {

    RespBean initializeDay(Date date, int step);

    RespBean updateNetWorth(Date dateId, String productId);
}
