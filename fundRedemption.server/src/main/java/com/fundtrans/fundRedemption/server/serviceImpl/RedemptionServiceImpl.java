package com.fundtrans.fundRedemption.server.serviceImpl;

import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.infoSearch.service.ShareService;
import com.fundtrans.pojo.*;
import com.fundtrans.fundRedemption.server.mapper.*;
import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.productManage.service.ProductService;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CloudComponent
public class RedemptionServiceImpl implements RedemptionService {

    private static final Log logger = LogFactory.getLog(RedemptionServiceImpl.class);

    @CloudReference
    private CardService cardService;
    @CloudReference
    private RecordService recordService;
    @CloudReference
    private ShareService shareService;
    @CloudReference
    private TrendService trendService;
    @CloudReference
    private ProductService productService;

    @Autowired
    private RtransMapper rtransMapper;
    @Autowired
    private RedemptionMapper redemptionMapper;


    @Override
    public RespBean doRedemption(Date date_now) {
        logger.info("写入赎回记录表");
        List<Rtrans> rtransList = new ArrayList<>();
        logger.info("当日三点前赎回交易记录查询（前一天三点后到当日三点前，未撤回的交易）");
        String date1 = DateFormatUtils.format(date_now, "yyyy-MM-dd 15:00:00");
        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date date = null;
        try {
            date = pattern.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);
        SimpleDateFormat spattern = new SimpleDateFormat("yyyy-MM-dd 15:00:00");
        String date2 = spattern.format(calendar.getTime());
        DateFormat pattern1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date3 = null;
        try {
            date3 = pattern1.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            rtransList = rtransMapper.findTodayrtrans(date, date3);
        } catch (Exception e) {
            logger.error("今日赎回交易记录查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.RTRANS_FIND_ERROR);
        }
        List<Redemption> redemptions = new ArrayList<>();
        for (int i = 0; i < rtransList.size(); ++i) {
            Rtrans temp = rtransList.get(i);
            Share share = null;
            try {
                logger.info("份额三要素查询");
                share = shareService.OutFindByThree(temp.getUser_id(), temp.getProduct_id(), temp.getCard_id());
            } catch (Exception e) {
                logger.error("份额三要素查询失败：" + e.getMessage());
                continue;
//                return RespBean.error(RespBeanEnum.SHARE_FIND_ERROR);
            }
            if (share == null) {
                logger.error("该用户在该卡中未持有该基金产品的份额");
//                return RespBean.error(RespBeanEnum.SHARE_CARD_NO_PURCHASE);
                continue;
            }
            Redemption redemption = new Redemption();
            redemption.setId(temp.getId());
            redemption.setUser_id(temp.getUser_id());
            redemption.setUser_name(temp.getUser_name());
            redemption.setProduct_id(temp.getProduct_id());
            redemption.setProduct_name(temp.getProduct_name());
            redemption.setCard_id(temp.getCard_id());
            redemption.setTime(temp.getTime());
            redemption.setCount(temp.getCount());
            /**
             * 调用净值计算模块，根据净值来计算赎回后可以获得的金额
             */
            Trend trend = null;
            try {
                logger.info("净值查询");
                trend = trendService.outTrendFindById(date, temp.getProduct_id());
            } catch (Exception e) {
                logger.error("净值查询失败：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.TREND_FIND_ERROR);
                continue;
            }
            if (trend == null) {
                logger.error("指定条件下无净值");
//                return RespBean.error(RespBeanEnum.TREND_NOT_EXIST);
                continue;
            }
            redemption.setAmount(trend.getPrice().multiply(temp.getCount()));
            try {
                logger.info("添加赎回记录：" + redemption.toString());
                redemptionMapper.addRedemption(redemption);
            } catch (Exception e) {
                logger.error("赎回记录添加失败：" + e.getMessage());
                continue;
//                return RespBean.error(RespBeanEnum.REDEMPTION_ADD_ERROR);
            }
            redemptions.add(redemption);

            //将rtrans的状态字段改为1 表示已处理
            try {
                rtransMapper.updateState(1, temp.getId());
            } catch (Exception e) {
                logger.error("赎回交易记录已处理更新失败：" + e.getMessage());
                redemptionMapper.deleteRedemption(redemption);
//                return RespBean.error(RespBeanEnum.RTRANS_STATE_UPDATE_FAIL);
                continue;
            }
            Record record = new Record();
            record.setId(temp.getId() + "r");
            record.setUser_id(temp.getUser_id());
            record.setProduct_id(temp.getProduct_id());
            record.setCard_id(temp.getCard_id());
            record.setTime(date_now);
            record.setNum("-" + temp.getCount());
            try {
                logger.info("添加份额流水记录：" + record.toString());
                recordService.OutAddRecord(record);
            } catch (Exception e) {
                logger.error("添加份额流水记录失败：" + e.getMessage());
                redemptionMapper.deleteRedemption(redemption);
                rtransMapper.updateState(0, temp.getId());
//                return RespBean.error(RespBeanEnum.RECORD_ADD_ERROR);
                continue;
            }
            share.setValue(share.getValue().subtract(temp.getCount()));
            share.setFrozen_num(share.getFrozen_num().subtract(temp.getCount()));
            if (share.getValue().compareTo(BigDecimal.valueOf(0)) == 0 && share.getFrozen_num().compareTo(BigDecimal.valueOf(0)) == 0) {
                try {
                    logger.info("该银行卡剩余份额为0，删除记录");
                    shareService.OutDeleteShare(share);
                } catch (Exception e) {
                    logger.info("份额记录删除失败");
                    redemptionMapper.deleteRedemption(redemption);
                    rtransMapper.updateState(0, temp.getId());
                    recordService.OutDeleteRecord(record.getId());
//                    return RespBean.error(RespBeanEnum.SHARE_DELETE_ERROR);
                    continue;
                }
            }
            try {
                logger.info("份额表赎回更新");
                shareService.OutUpdateCount(share);
            } catch (Exception e) {
                logger.error("份额表赎回更新失败：" + e.getMessage());
                redemptionMapper.deleteRedemption(redemption);
                rtransMapper.updateState(0, temp.getId());
                recordService.OutDeleteRecord(record.getId());
//                return RespBean.error(RespBeanEnum.SHARE_UPDATE_ERROR);
                continue;
            }

            //将收益转入银行卡
            Card card = null;
            try {
                logger.info("根据银行卡号查询银行卡");
                card = cardService.OutSelectByCardId(temp.getCard_id());
            } catch (Exception e) {
                logger.error("银行卡查询失败");
                redemptionMapper.deleteRedemption(redemption);
                rtransMapper.updateState(0, temp.getId());
                recordService.OutDeleteRecord(record.getId());
//                return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
                continue;
            }
            if (card == null) {
                logger.error("该赎回记录对应银行卡不存在");
                redemptionMapper.deleteRedemption(redemption);
                rtransMapper.updateState(0, temp.getId());
                recordService.OutDeleteRecord(record.getId());
//                return RespBean.error(RespBeanEnum.CARD_NOT_EXIST);
                continue;
            }
            card.setAccount(card.getAccount().add(redemption.getAmount()));
            try {
                logger.info("更新银行卡余额");
                cardService.OutUpdateCard(card);
            } catch (Exception e) {
                logger.error("银行卡余额更新失败");
                redemptionMapper.deleteRedemption(redemption);
                rtransMapper.updateState(0, temp.getId());
                recordService.OutDeleteRecord(record.getId());
//                return RespBean.error(RespBeanEnum.CARD_ACCOUNT_UPDATE_ERROR);
                continue;
            }

            logger.info("赎回成功");
        }
        return RespBean.success(redemptions);
    }

    @Override
    public RespBean updateRedemptionByDate(Date datetime) {
        logger.info("更新赎回表份额");
        int num = 0;
        List<Product> products = new ArrayList<Product>();
        try {
            // 将时间设置成每天的 15:00
            datetime = DateUtils.setHours(datetime, 15);
            products = productService.outFindAllProduct();
            for (Product product : products) {
                // 查询当前产品当天的基金走势，若不存在，则查询下一个产品
                Trend trend = trendService.outTrendFindById(datetime, product.getId());
                if (trend == null) {
                    logger.error(product.getId() + "基金该天走势不存在: " + RespBeanEnum.TREND_NOT_EXIST.getMessage());
                    continue;
                }
                // 生成当天的赎回订单
                try {
                    logger.info("生成当天赎回订单");
                    num = redemptionMapper.updateRedemptionByDate(datetime, product.getId(), trend.getPrice());
                } catch (Exception e) {
                    logger.error("生成当天赎回订单失败：" + e.getMessage());
                    return RespBean.error(RespBeanEnum.REDEMPTION_RELOAD_ERROR);
                }
            }
        } catch (Exception e) {
            logger.error("更新赎回表份额失败: " + e.getMessage());
            return RespBean.error(RespBeanEnum.REDEMPTION_UPDATEAMOUNT_ERROR);
        }
        logger.info("更新赎回表结束, 受影响赎回记录数: " + num);
        return RespBean.success(num);
    }

    @Override
    public RespBean getSum() {
        return RespBean.success(redemptionMapper.getSum());
    }

    @Override
    public RespBean findByDate(Datetime datetime) {
        return RespBean.success(redemptionMapper.findByDate(datetime.getDate1(),datetime.getDate2()));
    }

    @Override
    public RespBean findByUserId(String user_id) {
        return RespBean.success(redemptionMapper.findByUserId(user_id));
    }

    @Override
    public RespBean findByDateAndUserId(String user_id, Date date1, Date date2) {
        return RespBean.success(redemptionMapper.findByDateAndUserId(date1,date2,user_id));
    }

    @Override
    public RespBean findById(int id) {
        return RespBean.success(redemptionMapper.findById(id));
    }

    @Override
    public RespBean findByAll(TransSelectVo transSelectVo) {
        return RespBean.success(redemptionMapper.findByAll(transSelectVo));
    }
}
