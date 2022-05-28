package com.fundtrans.clearing.server.serviceImpl;

import com.fundtrans.pojo.Product;
import com.fundtrans.pojo.Trend;
import com.fundtrans.clearing.service.ClearingService;
import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.productManage.service.ProductService;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.utils.ClearingUtil;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.Date;

@CloudComponent
public class ClearingServiceImpl implements ClearingService {

    private static final Log logger = LogFactory.getLog(ClearingServiceImpl.class);

    @CloudReference
    private PurchaseService purchaseService;

    @CloudReference
    private RedemptionService redemptionService;

    @CloudReference
    private TrendService trendService;

    @CloudReference
    private ProductService productService;


    @Override
    public RespBean initializeDay(Date date, int step) {
        logger.info("日初始化");
        try {
            date = ClearingUtil.getNewDay(date, step);
        } catch (Exception e) {
            logger.error("日初始化失败: " + e.getMessage());
            return RespBean.error(RespBeanEnum.DATE_PARSE_ERROR);
        }
        logger.info("日初始化结束");
        return RespBean.success(date);
    }

    @Override
    public RespBean updateNetWorth(Date dateId, String productId) {
        logger.info("更新行情");
        BigDecimal netWorth;
        int num = 0;
        Product product = null;
        try {
            // 将时间设置成每天的 15:00
            dateId = DateUtils.addHours(dateId, 15);
            Date yesterday = ClearingUtil.getNewDay(dateId, -1);
            // 查询前一天的数据是否存在
            Trend trend = trendService.outTrendFindById(yesterday,productId);
            if (trend == null) {
                logger.error("更新行情失败: " + RespBeanEnum.TREND_NOT_EXIST.getMessage());
                return RespBean.error(RespBeanEnum.TREND_NOT_EXIST);
            }
            netWorth = ClearingUtil.getNewNetWorth(trend.getPrice());
            try {
                logger.info("更新行情");
                num = trendService.outTrendUpdate(dateId, productId, netWorth);
            }catch (Exception e){
                logger.error("更新行情失败：" + e.getMessage());
                return RespBean.error(RespBeanEnum.TREND_UPDATE_ERROR);
            }
            try{
                product = productService.outFindProductById(productId);
                if (product == null){
                    return RespBean.error(RespBeanEnum.PRODUCT_NOT_EXIST);
                }
                //设置涨跌幅
                product.setPrange((netWorth.subtract(trend.getPrice())).divide(trend.getPrice(),2));
                productService.updateProduct(product);
            }catch (Exception e){
                logger.error("产品涨跌幅更新失败："+e.getMessage());
                return RespBean.error(RespBeanEnum.ERROR);
            }
            // 重新生成当天的申购订单和赎回订单
//            try {
//                logger.info("重新生成当天申购订单");
//                purchaseService.updatePurchaseByDate(dateId, productId, netWorth);
//            }catch (Exception e){
//                logger.error("重新生成当天申购订单：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.PURCHASE_RELOAD_ERROR);
//            }
//            try {
//                logger.info("重新生成当天赎回订单");
//                redemptionService.updateRedemptionByDate(dateId, productId, netWorth);
//            }catch (Exception e){
//                logger.error("重新生成当天赎回订单：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.REDEMPTION_RELOAD_ERROR);
//            }
        } catch (Exception e) {
            logger.error("更新行情失败: " + e.getMessage());
            return RespBean.error(RespBeanEnum.TREND_UPDATE_ERROR);
        }
        logger.info("更新行情结束, 受影响产品走势数: " + num);
        return RespBean.success(product);
    }

}
