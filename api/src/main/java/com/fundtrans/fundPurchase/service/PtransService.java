package com.fundtrans.fundPurchase.service;


import com.fundtrans.pojo.Ptrans;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;
import java.util.List;

@CloudService
public interface PtransService {
    RespBean addPtrans(Ptrans ptrans, Date date);

    RespBean ownPtrans(String user_id);

    RespBean withdrawPtrans(Ptrans ptrans, Date date);

    RespBean getSum();

    RespBean findById(int id);

    RespBean findByAll(TransSelectVo transSelectVo);

    List<Ptrans> outFindTodayPtrans1(String user_id, String card_id, Date date1, Date date2);
}
