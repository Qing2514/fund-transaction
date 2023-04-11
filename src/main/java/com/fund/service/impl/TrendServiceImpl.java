package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Trend;
import com.fund.mapper.TrendMapper;
import com.fund.service.TrendService;
import com.fund.util.AjaxResult;
import com.fund.util.ClearingUtil;
import com.fund.util.ResultEnum;
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
    public AjaxResult findById(String productId){
        List<Trend> trends = trendMapper.findById(productId);
        if (trends == null){
            return AjaxResult.error(ResultEnum.TREND_NOT_EXIST);
        }
        return AjaxResult.success(trends);
    }

    @Override
    public AjaxResult addTrend(String productId){
        Trend temp  = trendMapper.getLateTrend(productId);
        Date date = ClearingUtil.getNewDate(temp.getDate());
        BigDecimal price = ClearingUtil.getNewNetWorth(temp.getPrice());
        Trend trend = new Trend(date, productId, price);
        trendMapper.addTrend(trend);
        return AjaxResult.success();
    }

}
