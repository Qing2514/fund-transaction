package com.fundtrans.productManage.server.serviceImpl;


import com.fundtrans.pojo.Trend;
import com.fundtrans.productManage.server.mapper.TrendMapper;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CloudComponent
public class TrendServiceImpl implements TrendService {
    private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private TrendMapper trendMapper;

    //根据时间查找净值，从指定日期到目前为止,近一个月、近三个月、近一年、近三年
    @Override
    public RespBean findTrendById(String product_id){
        logger.info("查询净值" + product_id);
        List<Trend> trends = new ArrayList<Trend>();
        try {
            logger.info("净值走势查询");
            trends = trendMapper.findTrendById(product_id);
        }catch (Exception e){
            logger.error("净值走势查询失败" + e.getMessage());
            return RespBean.error(RespBeanEnum.TREND_FIND_ERROR);
        }
        if (trends.isEmpty()){
            return RespBean.error(RespBeanEnum.TREND_NOT_EXIST);
        }
        logger.info("查询结束");
        return RespBean.success(trends);
    }

    //增加净值数据，每日清算时增加
    public RespBean addTrend(Trend trend,Date date_now){

        String date1 = DateFormatUtils.format(date_now, "yyyy-MM-dd 15:00:00");
        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date date = null;
        try {
            date = pattern.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        trend.setId(date);
        logger.info("增加净值数据:"+trend.toString());
        try {
            logger.info("添加净值");
            trendMapper.addTrend(trend);
        }catch (Exception e){
            logger.error("净值添加失败" + e.getMessage());
            return RespBean.error(RespBeanEnum.PRICE_INSERT_ERROR);
        }
        logger.info("产品添加成功");
        return RespBean.success();
    }

    @Override
    public Trend outTrendFindById(Date dateId, String productId) {
        return trendMapper.findById(dateId,productId);
    }

    @Override
    public int outTrendUpdate(Date dateId, String productId, BigDecimal netWorth) {
        return trendMapper.updateTrend(dateId,productId,netWorth);
    }

    @Override
    public RespBean getSum() {
        return RespBean.success(trendMapper.getSum());
    }

    @Override
    public Date outGetMaxDate() {
        return trendMapper.getMaxDate();
    }

    @Override
    public RespBean findByAll(TransSelectVo transSelectVo) {
        return RespBean.success(trendMapper.findByAll(transSelectVo));
    }

    @Override
    public void outAddTrend(Trend trend) {
        trendMapper.addTrend(trend);
    }
}
