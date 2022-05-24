package com.fundtrans.fundPurchase.service;


import com.fundtrans.pojo.Ptrans;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;

@CloudService
public interface PtransService {
    RespBean addPtrans(Ptrans ptrans, Date date);

    RespBean ownPtrans(String user_id);

    RespBean withdrawPtrans(Ptrans ptrans, Date date);
}
