package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Trend trend = new Trend(ClearingUtil.getDate(), productId, BigDecimal.valueOf(1.0000));
        return trendMapper.addTrend(trend);
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
                BigDecimal price = ClearingUtil.getNewNetWorth(temp.getNetWorth());
                trend = new Trend(date, productId, price);
            }
            else {
                return false;
            }
        }
        return trendMapper.addTrend(trend);
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
