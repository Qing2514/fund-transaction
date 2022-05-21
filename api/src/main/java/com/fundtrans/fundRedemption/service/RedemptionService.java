package com.fundtrans.fundRedemption.service;

import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface RedemptionService {
    RespBean doRedemption();
}
