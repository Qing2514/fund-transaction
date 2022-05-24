package com.fundtrans.fundPurchase.server.serviceImpl;

import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.pojo.Ptrans;
import com.fundtrans.fundPurchase.server.mapper.CardMapper;
import com.fundtrans.fundPurchase.server.mapper.PtransMapper;
import com.fundtrans.fundPurchase.server.mapper.UserMapper;
import com.fundtrans.fundPurchase.service.PtransService;
import com.fundtrans.pojo.Card;
import com.fundtrans.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CloudComponent
public class PtransServiceImpl implements PtransService {

    private static final Log logger = LogFactory.getLog(PtransServiceImpl.class);

    @CloudReference
    private CardService cardService;
    @CloudReference
    private UserService userService;

    @Autowired
    private PtransMapper ptransMapper;


    /**
     * 添加申购记录
     * 首先查询该记录中的银行卡ID，判断该银行卡存不存在
     * 其次对银行卡余额与购买金额进行判断，余额是否能够购买该基金（预扣减判断），若能，先不对其银行卡余额进行扣减，等到真正申购时进行余额扣减
     * 最后添加申购交易记录
     *
     * @param ptrans
     * @return
     */
    @Override
    public RespBean addPtrans(Ptrans ptrans,Date date) {
        String card_id = ptrans.getCard_id();
        BigDecimal amount = ptrans.getAmount();
        List<Card> cardList = cardService.OutFindByUserId(ptrans.getUser_id());
        if (cardList.size() == 0) {
            logger.error("该用户未绑定银行卡");
            return RespBean.error(RespBeanEnum.CARD_NUM_EMPTY);
        }
        int i = 0;
        for (; i < cardList.size(); i++) {
            if (cardList.get(i).getCard_id().equals(card_id)) {
                break;
            }
        }
        if (i >= cardList.size()) {
            logger.error("该银行卡不存在");
            return RespBean.error(RespBeanEnum.CARD_NOT_EXIST);
        }
        Card card = cardList.get(i);
        //若用户多次申购，则在每次申购时比较 银行卡余额 与 （之前未处理的申购交易记录的金额与当前申购金额的和）
        //是一种事前机制，当余额不足时及时提醒到用户，而不是在处理申购交易时再去进行判断
        //-------------------------------------------------------------
        //获得当前时间
        //获得当日下午三点的日期时间信息
        String date1 = DateFormatUtils.format(date, "yyyy-MM-dd 15:00:00");
        String date2 = DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
        DateFormat pattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date standard_date = null;
        try {
            standard_date = pattern.parse(date1);
            date = pattern.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //获得前一天的日期信息
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(standard_date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-24);
        SimpleDateFormat spattern = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date3 = spattern.format(calendar.getTime());
        DateFormat pattern1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date4=null;
        try {
            date4 = pattern1.parse(date3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Ptrans> ptransList = new ArrayList<>();
        try {
            if (date.before(standard_date)) {
                //判断昨日三点到此时（前提是今日三点前）申购交易的总金额是否超过银行卡余额
                ptransList = ptransMapper.findTodayPtrans1(ptrans.getUser_id(),ptrans.getCard_id(),date,date4);
            } else {
                //判断今日三点后到此时，申购交易总金额是否超过银行卡余额
                ptransList = ptransMapper.findTodayPtrans2(ptrans.getUser_id(),ptrans.getCard_id(),date,standard_date);
            }
        } catch (Exception e) {
            logger.error("申购交易记录查询失败");
            //-------------------------------------------------
            return RespBean.error(RespBeanEnum.ERROR);
        }
        BigDecimal purchase_amount = BigDecimal.valueOf(0);
        for (int k = 0; k < ptransList.size(); ++k) {
            purchase_amount = purchase_amount.add(ptransList.get(k).getAmount());
        }
        purchase_amount = purchase_amount.add(ptrans.getAmount());
        if (card.getAccount().compareTo(purchase_amount) == -1) {
            logger.error("该卡余额不足");
            return RespBean.error(RespBeanEnum.BALANCE_NOT_AVAILABLE);
        }
        ptrans.setId(0);
        ptrans.setTime(date);
        ptrans.setState(0);
        logger.info("添加申购交易记录：" + ptrans.toString());
        try {
            ptransMapper.addPtrans(ptrans);
        } catch (Exception e) {
            logger.error("申购交易记录添加失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.PTRANS_INSERT_FAIL);
        }
        logger.info("申购交易记录添加成功");
        return RespBean.success();
    }

    /**
     * 用户申购记录查询
     * 首先在用户表中查询是否存在该用户
     * 再根据用户ID去查其申购记录
     *
     * @param user_id
     * @return
     */
    @Override
    public RespBean ownPtrans(String user_id) {
        logger.info("查询用户申购交易记录");
        User user = null;
        try {
            user = userService.OutFindById(user_id);
        } catch (Exception e) {
            logger.error("用户查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
        }
        if (user == null) {
            logger.error("用户不存在");
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }
        List<Ptrans> ptransList = new ArrayList<>();
        try {
            ptransList = ptransMapper.findByUserId(user_id);
        } catch (Exception e) {
            logger.error("用户申购交易记录查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.PTRANS_FINDBYID_FAIL);
        }
        logger.info("用户交易记录查询成功");
        return RespBean.success(ptransList);
    }

    /**
     * 撤回申购记录
     * 首先根据ID去判断该申购记录是否存在
     * 其次对申购交易记录进行状态字段修改
     *
     * @param ptrans
     * @return
     */
    @Override
    public RespBean withdrawPtrans(Ptrans ptrans,Date date) {
        logger.info("撤回申购交易记录：" + ptrans.toString());
        Ptrans ptrans1 = null;
        try {
            ptrans1 = ptransMapper.findByPtransId(ptrans.getId());
        } catch (Exception e) {
            logger.error("申购交易记录查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.PTRASN_FINDBYPID_FAIL);
        }
        if (ptrans1 == null) {
            logger.error("申购交易记录不存在");
            return RespBean.error(RespBeanEnum.PTRANS_NOT_EXIST);
        }
        if (ptrans1.getState() != 0) {
            logger.error("申购交易记录已处理或者已撤回，无法进行撤回");
            return RespBean.error(RespBeanEnum.PTRANS_ALREADY_PROCESS);
        }
        //如果申购交易时间为三点前 且 撤回时间迟于当日三点 则撤单不成功
        //如果可以 在前端进行判断申购交易记录时间早于当日三点的记录 将撤回按钮隐藏

        String stop1 = DateFormatUtils.format(date, "yyyy-MM-dd 15:00:00");
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
        if (DateUtils.truncatedCompareTo(ptrans1.getTime(), start3, Calendar.SECOND) == 1 && DateUtils.truncatedCompareTo(ptrans1.getTime(), stop, Calendar.SECOND) == -1 &&
                DateUtils.truncatedCompareTo(date, stop, Calendar.SECOND) == 1) {
            logger.error("超过规定撤回时间，无法对交易进行撤回");
            return RespBean.error(RespBeanEnum.PTRANS_WITHDRAW_ERROR);
        }
        try {
            logger.info("修改申购交易记录状态字段");
            ptransMapper.updateState(2, ptrans.getId());
        } catch (Exception e) {
            logger.error("申购记录状态字段更新失败");
            return RespBean.error(RespBeanEnum.PTRANS_STATE_UPDATE_FAIL);
        }
        logger.info("申购交易记录撤回成功");
        return RespBean.success();
    }
}
