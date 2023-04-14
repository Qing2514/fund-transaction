package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Trend;
import com.fund.mapper.TrendMapper;
import com.fund.service.TrendService;
import com.fund.util.ClearingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TrendServiceImpl extends ServiceImpl<TrendMapper, Trend> implements TrendService {

    @Autowired
    private TrendMapper trendMapper;

    @Override
    public List<Trend> findById(String productId) {
        return trendMapper.findByProductId(productId);
    }

    @Override
    public boolean addTrend(String productId) {
        Trend temp = trendMapper.getLateTrend(productId);
        Trend trend;
        if (temp == null) {
            trend = new Trend(ClearingUtil.getDate(), productId, BigDecimal.valueOf(1.0000));
        } else {
            Date date = ClearingUtil.getNewDate(temp.getDate());
            BigDecimal price = ClearingUtil.getNewNetWorth(temp.getPrice());
            trend = new Trend(date, productId, price);
        }
        return trendMapper.addTrend(trend);
    }

    @Override
    public BigDecimal getPrice(String productId, Date date) {
        Trend trend = trendMapper.findByProductIdAndDate(productId, date);
        if(trend == null) {
            return BigDecimal.ZERO;
        }
        return trend.getPrice();
    }

}
