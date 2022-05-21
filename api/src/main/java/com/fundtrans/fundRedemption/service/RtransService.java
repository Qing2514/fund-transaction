package com.fundtrans.fundRedemption.service;

import com.fundtrans.fundRedemption.pojo.Rtrans;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface RtransService {
    RespBean addRtrans(Rtrans rtrans);

    RespBean ownRtrans(String user_id);

    RespBean withdrawRtrans(Rtrans rtrans);
}
