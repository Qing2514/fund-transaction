package com.fundtrans.fundRedemption.server.serviceImpl;

import com.fundtrans.fundPurchase.pojo.Ptrans;
import com.fundtrans.fundRedemption.pojo.Rtrans;
import com.fundtrans.fundRedemption.pojo.Share;
import com.fundtrans.fundRedemption.server.mapper.CardMapper;
import com.fundtrans.fundRedemption.server.mapper.RtransMapper;
import com.fundtrans.fundRedemption.server.mapper.ShareMapper;
import com.fundtrans.fundRedemption.server.mapper.UserMapper;
import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.fundRedemption.service.RtransService;
import com.fundtrans.userManage.pojo.Card;
import com.fundtrans.userManage.pojo.User;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CloudComponent
public class RtransServiceImpl implements RtransService {

    private static final Log logger = LogFactory.getLog(RtransServiceImpl.class);

    @Autowired
    private RtransMapper rtransMapper;
    @Autowired
    private ShareMapper shareMapper;
    @Autowired
    private UserMapper userMapper;



    @Override
    public RespBean addRtrans(Rtrans rtrans) {

        //首先判断赎回银行卡是否在份额表中该用户对应该基金产品持有份额对应的银行卡列表中
        String card_id = rtrans.getCard_id();
        List<Share> shareList = new ArrayList<>();
        try {
            shareList = shareMapper.judgeCard(rtrans.getUser_id(),rtrans.getProduct_id());
        }catch (Exception e){
            logger.error("份额表根据用户与基金查询银行卡失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.SHARE_FIND_ERROR);
        }
        if (shareList.size() == 0){
            logger.error("该用户未购买该基金产品，无法进行赎回");
            return RespBean.error(RespBeanEnum.SHARE_NO_PURCHASE);
        }
        int i = 0;
        for (; i < shareList.size(); ++i){
            if (card_id.equals(shareList.get(i).getCard_id())){
                break;
            }
        }
        if (i >= shareList.size()){
            logger.error("该用户未用该卡进行申购，无法赎回到该卡中");
            return RespBean.error(RespBeanEnum.ERROR);
        }
        //判断赎回份额是否大于该卡已购的份额

        //若存在用户不断进行赎回操作，获取前天下午三点后到当今时间的之前未处理的赎回交易记录，计算总共需要赎回的份额与剩余份额，再与
        //还要进行赎回的份额相比较，判断是否超份额赎回
        Share share = shareList.get(i);
        if (share.getNum().compareTo(share.getFrozen_num().add(rtrans.getCount())) == -1){
            logger.error("赎回份额不能大于该卡已购份额");
            return RespBean.error(RespBeanEnum.RTRANS_COUNT_BEYOND);
        }

        //完成判断 能够添加到赎回交易记录中
        rtrans.setId(0);
        rtrans.setTime(new Date());
        rtrans.setState(0);
        logger.info("添加赎回交易记录"+rtrans.toString());
        try {
            rtransMapper.addRtrans(rtrans);
        }catch (Exception e){
            logger.error("添加赎回交易记录失败"+e.getMessage());
            return RespBean.error(RespBeanEnum.RTRANS_ADD_ERROR);
        }
        share.setFrozen_num(share.getFrozen_num().add(rtrans.getCount()));
        try {
            shareMapper.updateCount(share);
        }catch (Exception e){
            logger.error("冻结份额更新失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.SHARE_FROZEN_UPDATE_ERROR);
        }

        return RespBean.success();
    }

    @Override
    public RespBean ownRtrans(String user_id) {
        logger.info("查询用户申购交易记录");
        User user = null;
        try {
            user = userMapper.findById(user_id);
        }catch (Exception e){
            logger.error("用户查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
        }
        if (user == null){
            logger.error("用户不存在");
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        List<Rtrans> rtransList = new ArrayList<>();
        try {
            rtransList = rtransMapper.findByUserId(user_id);
        }catch (Exception e){
            logger.error("用户赎回交易记录查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.RTRANS_FIND_ERROR);
        }
        logger.info("用户赎回交易记录查询成功");
        return RespBean.success(rtransList);
    }

    @Override
    public RespBean withdrawRtrans(Rtrans rtrans) {
        Date date = new Date();
        logger.info("撤回赎回交易记录："+rtrans.toString());
        Rtrans rtrans1 = null;
        try {
            rtrans1 = rtransMapper.findByRtransId(rtrans.getId());
        }catch (Exception e){
            logger.error("赎回交易记录查询失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.RTRANS_FIND_ERROR);
        }
        if (rtrans1 == null){
            logger.error("赎回交易记录不存在");
            return RespBean.error(RespBeanEnum.RTRANS_NOT_EXIST);
        }
        if (rtrans1.getState() != 0){
            logger.error("赎回交易记录已处理或者已撤回，无法进行撤回");
            return RespBean.error(RespBeanEnum.RTRANS_ALREADY_PROCEED);
        }
        //如果赎回交易时间为三点前 且 撤回时间迟于当日三点 则撤单不成功
        //如果可以 在前端进行判断赎回交易记录时间早于当日三点的记录 将撤回按钮隐藏
        String stop1 = DateFormatUtils.format(new Date(), "yyyy-MM-dd 15:00:00");
        DateFormat stop2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date stop = null;
        try {
            stop = stop2.parse(stop1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stop);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);
        SimpleDateFormat start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start1 = start.format(calendar.getTime());
        DateFormat start2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start3 = null;
        try {
            start3 = start2.parse(start1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //若申购交易记录时间在昨日三点后今日三点前，且撤回时间在三点后，则撤回失败
        if (DateUtils.truncatedCompareTo(rtrans1.getTime(), start3, Calendar.SECOND) == 1 && DateUtils.truncatedCompareTo(rtrans1.getTime(), stop, Calendar.SECOND) == -1 &&
                DateUtils.truncatedCompareTo(date, stop, Calendar.SECOND) == 1) {
            logger.error("超过规定撤回时间，无法对交易进行撤回");
            return RespBean.error(RespBeanEnum.RTRANS_WITHDRAW_ERROR);
        }
        Share share = null;
        try {
            share = shareMapper.findByThree(rtrans.getUser_id(),rtrans.getProduct_id(),rtrans.getCard_id());
        }catch (Exception e){
            logger.error("份额查询失败" + e.getMessage());
            return RespBean.error(RespBeanEnum.SHARE_FIND_ERROR);
        }
        if (share == null){
            logger.error("该用户未用该卡进行申购，无法赎回到该卡中");
            return RespBean.error(RespBeanEnum.SHARE_CARD_NO_PURCHASE);
        }
        share.setFrozen_num(BigDecimal.valueOf(0));
        try {
            shareMapper.updateCount(share);
        }catch (Exception e){
            logger.error("冻结份额回滚失败："+e.getMessage());
            return RespBean.error(RespBeanEnum.SHARE_FROZEN_UPDATE_ERROR);
        }
        try {
            logger.info("修改赎回交易记录状态字段");
            rtransMapper.updateState(2,rtrans.getId());
        }catch (Exception e){
            logger.error("赎回记录状态字段更新失败");
            return RespBean.error(RespBeanEnum.RTRANS_STATE_UPDATE_FAIL);
        }
        logger.info("赎回交易记录撤回成功");
        return RespBean.success();
    }


}
