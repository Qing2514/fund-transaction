package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Trend;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TrendService extends IService<Trend> {

    List<Trend> findById(String productId);

    boolean addTrendByProductId(String productId);

    boolean addTrendByDate(Date date);

    BigDecimal getPrice(String productId, Date date);

}
