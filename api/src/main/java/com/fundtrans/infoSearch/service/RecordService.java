package com.fundtrans.infoSearch.service;

import com.fundtrans.pojo.Record;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

@CloudService
public interface RecordService {

    RespBean findByUserIdAndProductId(String userId, String productId);

    void OutAddRecord(Record record);

    void OutDeleteRecord(String id);
}
