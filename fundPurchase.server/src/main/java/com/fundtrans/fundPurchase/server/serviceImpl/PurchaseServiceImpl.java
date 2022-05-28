package com.fundtrans.fundPurchase.server.serviceImpl;

import com.fundtrans.pojo.*;
import com.fundtrans.fundPurchase.server.mapper.*;
import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.infoSearch.service.ShareService;
import com.fundtrans.productManage.service.ProductService;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CloudComponent
public class PurchaseServiceImpl implements PurchaseService {

    private static final Log logger = LogFactory.getLog(PurchaseServiceImpl.class);

    @CloudReference
    private ShareService shareService;
    @CloudReference
    private UserService userService;
    @CloudReference
    private CardService cardService;
    @CloudReference
    private RecordService recordService;
    @CloudReference
    private TrendService trendService;
    @CloudReference
    private ProductService productService;

    @Autowired
    private PtransMapper ptransMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;


    /**
     * 三点后自动将用户提交的申购交易记录写入申购记录表
     *
     * @return
     */
    @Override
    public RespBean doPurchase(Date date_now) {

        logger.info("写入申购记录表");
        List<Ptrans> ptransList = new ArrayList<>();
        /**
         * 数据库如何根据日期进行判断交易，前天三点后到今天三点前的未处理的申购交易记录 state=0
         */
        logger.info("当日三点前申购交易记录查询（前一天三点后到当日三点前，未撤回的交易）");
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
            ptransList = ptransMapper.findTodayptrans(date, date3);
        } catch (Exception e) {
            logger.error("今日申购交易记录查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.ERROR);
        }
        List<Purchase> purchases = new ArrayList<>();
        //--------------------------------------------------------------------
        //希望每个循环中的整个操作具有原子性，要么在出现报错时回滚，跳到对下一条记录进行操作，要么把全部操作做完
        for (int i = 0; i < ptransList.size(); i++) {
            Ptrans temp = ptransList.get(i);
            String card_id = temp.getCard_id();
            BigDecimal amount = temp.getAmount();
            Card card = cardService.OutSelectByCardId(card_id);
            if (card == null) {
                logger.error("记录：" + temp.getId() + "银行卡不存在");
//                return RespBean.error(RespBeanEnum.CARD_NOT_EXIST);
                continue;
            }
            if (card.getAccount().compareTo(amount) == -1) {
                logger.error("记录：" + temp.getId() + "余额不足");
//                return RespBean.error(RespBeanEnum.BALANCE_NOT_AVAILABLE);
                continue;
            }
            card.setAccount(card.getAccount().subtract(amount));
            try {
                cardService.OutUpdateCard(card);
            } catch (Exception e) {
                logger.error("记录：" + temp.getId() + "银行卡信息余额扣减失败：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.CARD_UPDATE_FAIL);
                continue;
            }
            try {
                ptransMapper.updateState(1, temp.getId());
            } catch (Exception e) {
                logger.error("记录：" + temp.getId() + "申购交易记录已处理更新失败" + e.getMessage());
//                return RespBean.error(RespBeanEnum.PTRANS_STATE_UPDATE_FAIL);
                //银行卡余额扣减的回滚操作
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                continue;
            }
            Purchase purchase = new Purchase();
            purchase.setId(temp.getId());
            purchase.setUser_id(temp.getUser_id());
            purchase.setProduct_id(temp.getProduct_id());
            purchase.setCard_id(temp.getCard_id());
            purchase.setTime(temp.getTime());
            purchase.setAmount(temp.getAmount());
            /**
             * 调用净值计算模块，根据净值来计算份额
             */
            Trend trend = null;
            try {
                logger.info("净值查询");
                trend = trendService.outTrendFindById(date, temp.getProduct_id());
            }catch (Exception e){
                logger.error("净值查询失败：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.TREND_FIND_ERROR);
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                ptransMapper.updateState(0, temp.getId());
                continue;
            }
            if (trend == null){
                logger.error("指定条件下无净值");
//                return RespBean.error(RespBeanEnum.TREND_NOT_EXIST);
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                ptransMapper.updateState(0, temp.getId());
                continue;
            }
            purchase.setCount(temp.getAmount().divide(trend.getPrice(),2, RoundingMode.HALF_UP));
            try {
                logger.info("记录：" + temp.getId() + "添加申购记录：" + purchase.toString());
                purchaseMapper.addPurchase(purchase);
            } catch (Exception e) {
                logger.error("记录：" + temp.getId() + "申购记录添加失败：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.ERROR);
                //回滚操作：扣减余额增加，申购交易记录字段设回0
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                ptransMapper.updateState(0, temp.getId());
                continue;
            }
            purchases.add(purchase);

            Record record = new Record();
            record.setId(purchase.getId() + "p");
            record.setUser_id(purchase.getUser_id());
            record.setProduct_id(purchase.getProduct_id());
            record.setCard_id(purchase.getCard_id());
            record.setTime(purchase.getTime());
            record.setNum("+" + purchase.getCount());
            try {
                logger.info("记录：" + temp.getId() + "添加份额流水表");
                recordService.OutAddRecord(record);
            } catch (Exception e) {
                logger.error("记录：" + temp.getId() + "添加份额流水表失败：" + e.getMessage());
                //-------------------------------------------
//                return RespBean.error(RespBeanEnum.ERROR);

                //回滚操作：扣减余额增加，申购交易记录字段设回0，申购记录删除
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                ptransMapper.updateState(0, temp.getId());
                purchaseMapper.deletePurchase(purchase.getId());
                continue;
            }
            Share share = null;
            try {
                share = shareService.OutFindByThree(purchase.getUser_id(), purchase.getProduct_id(), purchase.getCard_id());
            } catch (Exception e) {
                logger.error("记录：" + temp.getId() + "查找份额表失败：" + e.getMessage());
//                return RespBean.error(RespBeanEnum.ERROR);

                //回滚操作：扣减余额增加，申购交易记录字段设回0，申购记录删除，删除份额流水记录
                card.setAccount(card.getAccount().add(amount));
                cardService.OutUpdateCard(card);
                ptransMapper.updateState(0, temp.getId());
                purchaseMapper.deletePurchase(purchase.getId());
                recordService.OutDeleteRecord(record.getId());
                continue;
            }
            //若份额表中无该用户，该基金，该银行卡对应的份额记录，则添加
            if (share == null) {
                logger.info("添加记录：" + temp.getId() + "份额记录");
                share = new Share();
                share.setUser_id(purchase.getUser_id());
                share.setProduct_id(purchase.getProduct_id());
                Product product = null;
                product = productService.outFindProductById(purchase.getProduct_id());
                share.setName(product.getName());
                share.setCard_id(purchase.getCard_id());
                share.setValue(purchase.getCount());
                share.setFrozen_num(BigDecimal.valueOf(0));
                try {
                    shareService.OutAddShare(share);
                } catch (Exception e) {
                    logger.error("记录：" + temp.getId() + "份额记录添加失败：" + e.getMessage());
                    //--------------------------------------
//                    return RespBean.error(RespBeanEnum.ERROR);

                    //回滚操作：扣减余额增加，申购交易记录字段设回0，申购记录删除，删除份额流水记录
                    card.setAccount(card.getAccount().add(amount));
                    cardService.OutUpdateCard(card);
                    ptransMapper.updateState(0, temp.getId());
                    purchaseMapper.deletePurchase(purchase.getId());
                    recordService.OutDeleteRecord(record.getId());
                    continue;
                }
            }
            //否则进行更新修改
            else {
                logger.info("更新记录：" + temp.getId() + "份额记录");
                try {
                    share.setValue(share.getValue().add(purchase.getCount()));
                    shareService.OutUpdateShareAdd(share);
                } catch (Exception e) {
                    logger.error("记录：" + temp.getId() + "份额记录更新失败：" + e.getMessage());
                    //---------------------------------------
//                    return RespBean.error(RespBeanEnum.ERROR);

                    //回滚操作：扣减余额增加，申购交易记录字段设回0，申购记录删除，删除份额流水记录
                    card.setAccount(card.getAccount().add(amount));
                    cardService.OutUpdateCard(card);
                    ptransMapper.updateState(0, temp.getId());
                    purchaseMapper.deletePurchase(purchase.getId());
                    recordService.OutDeleteRecord(record.getId());
                    continue;
                }
            }
            logger.info("记录：" + temp.getId() + "添加成功");
        }
        return RespBean.success(purchases);
    }

    @Override
    public RespBean updatePurchaseByDate(Date datetime) {
        logger.info("更新申购表份额");
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
                // 生成当天的申购订单
                try {
                    logger.info("生成当天申购订单");
                    num = purchaseMapper.updatePurchaseByDate(datetime, product.getId(), trend.getPrice());
                } catch (Exception e) {
                    logger.error("生成当天申购订单失败：" + e.getMessage());
                    return RespBean.error(RespBeanEnum.PURCHASE_RELOAD_ERROR);
                }
            }
        } catch (Exception e) {
            logger.error("更新申购表份额失败: " + e.getMessage());
            return RespBean.error(RespBeanEnum.PURCHASE_UPDATECOUNT_ERROR);
        }
        logger.info("更新申购表结束, 受影响申购记录数: " + num);
        return RespBean.success(num);
    }

    @Override
    public RespBean getSum() {
        return RespBean.success(purchaseMapper.getSum());
    }

    @Override
    public RespBean findById(int id) {
        return RespBean.success(purchaseMapper.findById(id));
    }
}
