package com.fundtrans.fundPurchase.service;

import com.fundtrans.fundPurchase.pojo.Ptrans;
import com.fundtrans.fundPurchase.pojo.Purchase;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface PurchaseService {
    RespBean doPurchase();
}
