package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.entity.Trend;
import com.fund.mapper.TrendMapper;
import com.fund.service.ProductService;
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

    @Autowired
    private ProductService productService;

    @Override
    public List<Trend> findById(String productId) {
        return trendMapper.findByProductId(productId);
    }

    @Override
    public boolean addTrendByProductId(String productId) {
        Trend temp = trendMapper.getLateTrend(productId);
        Trend trend;
        if(temp == null) {
            trend = new Trend(ClearingUtil.getDate(new Date()), BigDecimal.valueOf(1.0000),
                    BigDecimal.valueOf(0.00));
        }
        else {
            Date date = temp.getDate();
            BigDecimal newNetWorth = ClearingUtil.getNewNetWorth(temp.getNetWorth());
            BigDecimal lateNetWorth = trendMapper.getLateTrend(productId).getNetWorth();
            BigDecimal growth = newNetWorth.subtract(lateNetWorth).divide(lateNetWorth, 4);
            trend = new Trend(ClearingUtil.addDate(date), newNetWorth, growth);
        }
        return trendMapper.addTrend(productId, trend);
    }

    @Override
    public boolean addTrendByDate(Date date) {
        // 若为周末，不让更新
        if(ClearingUtil.isWeekend(date)) {
            return false;
        }
        List<String> productIds = productService.getIds();
        Trend trend = null;
        for(String productId : productIds) {
            Trend temp = trendMapper.getLateTrend(productId);
            if(date.after(temp.getDate())) {
                BigDecimal newNetWorth = ClearingUtil.getNewNetWorth(temp.getNetWorth());
                BigDecimal lateNetWorth = trendMapper.getLateTrend(productId).getNetWorth();
                BigDecimal growth = newNetWorth.subtract(lateNetWorth).divide(lateNetWorth, 4);
                trend = new Trend(ClearingUtil.addDate(date), newNetWorth, growth);
                trendMapper.addTrend(productId, trend);
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public BigDecimal getPrice(String productId, Date date) {
        Trend trend = trendMapper.findByProductIdAndDate(productId, date);
        if(trend == null) {
            return BigDecimal.ZERO;
        }
        return trend.getNetWorth();
    }

}
