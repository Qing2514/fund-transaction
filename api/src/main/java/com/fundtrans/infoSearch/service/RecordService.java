package com.fundtrans.infoSearch.service;

import com.fundtrans.pojo.Record;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.Date;

@CloudService
public interface RecordService {

    void OutAddRecord(Record record);

    void OutDeleteRecord(String id);

    RespBean getSum();

    RespBean findByUserId(String user_id);

    RespBean findByDate(Date date1, Date date2);

    RespBean findByDateAndUserId(Date date1, Date date2, String id);

    RespBean findByAll(TransSelectVo transSelectVo);
}
