package com.fundtrans.fundRedemption.service;


import com.fundtrans.pojo.Rtrans;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;

@CloudService
public interface RtransService {
    RespBean addRtrans(Rtrans rtrans, Date date);

    RespBean ownRtrans(String user_id);

    RespBean withdrawRtrans(Rtrans rtrans, Date date);

    RespBean getSum();

    RespBean findByUserId(String id);

    RespBean findById(int id);

    RespBean findByAll(TransSelectVo transSelectVo);
}
