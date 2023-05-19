package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.entity.Trend;
import com.fund.mapper.TrendMapper;
import com.fund.service.ProductService;
import com.fund.service.TrendService;
import com.fund.util.ClearingUtil;
import com.fund.vo.IncomeVo;
import com.fund.vo.PerformanceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
            trend = new Trend(ClearingUtil.addDate(date, 1), newNetWorth, growth);
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
        Trend trend;
        for(String productId : productIds) {
            Trend temp = trendMapper.getLateTrend(productId);
            if(date.after(temp.getDate())) {
                BigDecimal newNetWorth = ClearingUtil.getNewNetWorth(temp.getNetWorth());
                BigDecimal lateNetWorth = trendMapper.getLateTrend(productId).getNetWorth();
                BigDecimal growth = newNetWorth.subtract(lateNetWorth).divide(lateNetWorth, 4);
                trend = new Trend(ClearingUtil.addDate(date, 1), newNetWorth, growth);
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

    @Override
    public IncomeVo compareIncomeByDays(String productId, Integer days, BigDecimal amount, Integer frequency) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0 || frequency <= 0) {
            return null;
        }
        Trend trend = trendMapper.getLateTrend(productId);
        Date endDate = trend.getDate();
        Date startDate = ClearingUtil.addDate(endDate, -days);
        List<Trend> trendList = trendMapper.findByDate(productId, startDate, endDate);
        if(trendList.isEmpty()) {
            return null;
        }
        int count = 0;
        BigDecimal startNetWorth = trendList.get(0).getNetWorth();
        BigDecimal endNetWorth = trendList.get(trendList.size() - 1).getNetWorth();
        // 定投能获得的收益
        BigDecimal investmentShare = BigDecimal.ZERO;
        Date dateFrequency = trendList.get(0).getDate();
        for (Trend value : trendList) {
            // 定期买入
            if (dateFrequency.compareTo(value.getDate()) == 0) {
                count++;
                investmentShare = investmentShare.add(amount.divide(value.getNetWorth(), 4));
                // 更新下一个申购日（frequency天后的该天净值可能不存在，需要从数据库中筛选出该天后最近的一天）
                dateFrequency = ClearingUtil.addDate(dateFrequency, frequency);
                if(dateFrequency.compareTo(endDate) <= 0) {
                    dateFrequency = trendMapper.findOneByDate(productId, dateFrequency, endDate).getDate();
                }
            }
        }
        BigDecimal investmentIncome = investmentShare.multiply(endNetWorth).subtract(amount);
        // 全额购入能获得的收益
        BigDecimal purchaseShare = amount.multiply(BigDecimal.valueOf(count)).divide(startNetWorth, 4);
        BigDecimal purchaseIncome = purchaseShare.multiply(endNetWorth).subtract(amount);
        BigDecimal allAmount = amount.multiply(BigDecimal.valueOf(count));
        return new IncomeVo(allAmount, purchaseIncome, investmentIncome);
    }

    @Override
    public IncomeVo compareIncomeByDate(String productId, Date startDate, Date endDate, BigDecimal amount,
                                        Integer frequency) {
        if(startDate.compareTo(endDate) >= 0 || amount.compareTo(BigDecimal.ZERO) <= 0 || frequency <= 0) {
            return null;
        }
        List<Trend> trendList = trendMapper.findByDate(productId, startDate, endDate);
        if(trendList.isEmpty()) {
            return null;
        }
        int count = 0;
        BigDecimal startNetWorth = trendList.get(0).getNetWorth();
        BigDecimal endNetWorth = trendList.get(trendList.size() - 1).getNetWorth();
        // 定投能获得的收益
        BigDecimal investmentShare = BigDecimal.ZERO;
        Date dateFrequency = trendList.get(0).getDate();
        for (Trend value : trendList) {
            // 定期买入
            if (dateFrequency.compareTo(value.getDate()) == 0) {
                count++;
                investmentShare = investmentShare.add(amount.divide(value.getNetWorth(), 4));
                // 更新下一个申购日（frequency天后的该天净值可能不存在，需要从数据库中筛选出该天后最近的一天）
                dateFrequency = ClearingUtil.addDate(dateFrequency, frequency);
                if(dateFrequency.compareTo(endDate) <= 0) {
                    dateFrequency = trendMapper.findOneByDate(productId, dateFrequency, endDate).getDate();
                }
            }
        }
        BigDecimal investmentIncome = investmentShare.multiply(endNetWorth).subtract(amount);
        // 全额购入能获得的收益
        BigDecimal purchaseShare = amount.multiply(BigDecimal.valueOf(count)).divide(startNetWorth, 4);
        BigDecimal purchaseIncome = purchaseShare.multiply(endNetWorth).subtract(amount);
        BigDecimal allAmount = amount.multiply(BigDecimal.valueOf(count));
        return new IncomeVo(allAmount, purchaseIncome, investmentIncome);
    }

    @Override
    public List<PerformanceVo> getPerformance(String productId) {
        List<Trend> trendList = trendMapper.findByProductId(productId);
        if(trendList.isEmpty()) {
            return null;
        }
        Trend lateTrend = trendMapper.getLateTrend(productId);
        BigDecimal lateNetWorth = lateTrend.getNetWorth();
        Date lateDate = lateTrend.getDate();

        Date oneWeek = ClearingUtil.addDate(lateDate, -7);
        Date oneMonth = ClearingUtil.addDate(lateDate, -30);
        Date threeMonth = ClearingUtil.addDate(lateDate, -90);
        Date sixMonth = ClearingUtil.addDate(lateDate, -180);
        Date oneYear = ClearingUtil.addDate(lateDate, -365);

        BigDecimal oneWeekNetWorth = trendMapper.findOneByDate(productId, oneWeek, lateDate).getNetWorth();
        BigDecimal oneMonthNetWorth = trendMapper.findOneByDate(productId, oneMonth, lateDate).getNetWorth();
        BigDecimal threeMonthNetWorth = trendMapper.findOneByDate(productId, threeMonth, lateDate).getNetWorth();
        BigDecimal sixMonthNetWorth = trendMapper.findOneByDate(productId, sixMonth, lateDate).getNetWorth();
        BigDecimal oneYearNetWorth = trendMapper.findOneByDate(productId, oneYear, lateDate).getNetWorth();

        BigDecimal oneWeekGrowth = lateNetWorth.subtract(oneWeekNetWorth).divide(oneWeekNetWorth, 4)
                .multiply(BigDecimal.valueOf(100));
        BigDecimal oneMonthGrowth = lateNetWorth.subtract(oneMonthNetWorth).divide(oneMonthNetWorth, 4)
                .multiply(BigDecimal.valueOf(100));
        BigDecimal threeMonthGrowth = lateNetWorth.subtract(threeMonthNetWorth).divide(threeMonthNetWorth, 4)
                .multiply(BigDecimal.valueOf(100));
        BigDecimal sixMonthGrowth = lateNetWorth.subtract(sixMonthNetWorth).divide(sixMonthNetWorth, 4)
                .multiply(BigDecimal.valueOf(100));
        BigDecimal oneYearGrowth = lateNetWorth.subtract(oneYearNetWorth).divide(oneYearNetWorth, 4)
                .multiply(BigDecimal.valueOf(100));

        List<PerformanceVo> performanceVoList = new ArrayList<>();
        performanceVoList.add(new PerformanceVo("近1周", oneWeekGrowth));
        performanceVoList.add(new PerformanceVo("近1月", oneMonthGrowth));
        performanceVoList.add(new PerformanceVo("近3月", threeMonthGrowth));
        performanceVoList.add(new PerformanceVo("近6月", sixMonthGrowth));
        performanceVoList.add(new PerformanceVo("近1年", oneYearGrowth));
        return performanceVoList;
    }

}
