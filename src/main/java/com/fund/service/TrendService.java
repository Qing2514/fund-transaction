package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Trend;
import com.fund.util.AjaxResult;

public interface TrendService extends IService<Trend> {

    AjaxResult findById(String productId);

    AjaxResult addTrend(String productId);

}
