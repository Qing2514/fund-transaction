package com.fundtrans.fundPurchase.service;

import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface ProductService {
    RespBean selectByProductId(String id);

    RespBean selectByProductName(String name);

    RespBean selectUserByName(String name);
}
