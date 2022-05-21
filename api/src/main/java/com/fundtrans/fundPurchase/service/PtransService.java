package com.fundtrans.fundPurchase.service;

import com.fundtrans.fundPurchase.pojo.Ptrans;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface PtransService {
    RespBean addPtrans(Ptrans ptrans);

    RespBean ownPtrans(String user_id);

    RespBean withdrawPtrans(Ptrans ptrans);
}
