package com.fundtrans.productManage.service;


import com.fundtrans.pojo.Trend;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.Date;

@CloudService
public interface TrendService {
    //根据时间查找净值，从指定日期到目前为止,近一个月、近三个月、近一年、近三年
    public RespBean findTrendById(String product_id);
    //增加净值数据，每日清算时增加
    public RespBean addTrend(Trend trend);

    Trend outTrendFindById(Date dateId, String productId);

    public int outTrendUpdate(Date dateId,String productId,BigDecimal netWorth);

    RespBean getSum();

    Date outGetMaxDate();
}
