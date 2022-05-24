package com.fundtrans.fundRedemption.service;


import com.fundtrans.pojo.Rtrans;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;

@CloudService
public interface RtransService {
    RespBean addRtrans(Rtrans rtrans, Date date);

    RespBean ownRtrans(String user_id);

    RespBean withdrawRtrans(Rtrans rtrans, Date date);
}
