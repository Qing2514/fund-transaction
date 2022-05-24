package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.pojo.Card;
import com.fundtrans.infoSearch.server.mapper.CardMapper;
import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.pojo.User;
import com.fundtrans.userManage.service.UserService;
import com.fundtrans.infoSearch.vo.CardVo;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.RespBeanEnum;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CloudComponent
public class CardServiceImpl implements CardService {

    private static final Log logger = LogFactory.getLog(CardServiceImpl.class);

    @CloudReference
    private UserService userService;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public RespBean findByUserId(String userId) {
        logger.info("查询用户银行卡");
        List<Card> cardList = new ArrayList<>();
        try {
            cardList = cardMapper.findByUserId(userId);
        } catch (Exception e) {
            logger.error("查询用户银行卡失败: " + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
        }
        if (cardList == null) {
            return RespBean.error(RespBeanEnum.CARD_NUM_EMPTY);
        }
        logger.info("查询结束");
        return RespBean.success(cardList);
    }

    @Override
    public List<Card> OutFindByUserId(String userId) {
        return cardMapper.findByUserId(userId);
    }

    @Override
    public void OutAddCard(int id, String card_id, String user_id, BigDecimal account) {
        cardMapper.addCard(id, card_id, user_id, account);
    }

    @Override
    public void OutDeleteCard(String card_id) {
        cardMapper.deleteCard(card_id);
    }

    @Override
    public Card OutSelectByCardId(String card_id) {
        return cardMapper.selectByCardId(card_id);
    }

    @Override
    public void OutAddCardAccount(BigDecimal amount, String card_id) {
        cardMapper.addCardAccount(amount, card_id);
    }

    @Override
    public List<Card> OutFindAllCard(String user_id) {
        return cardMapper.findAllCard(user_id);
    }

    @Override
    public void OutUpdateCard(Card card) {
        cardMapper.update(card);
    }

    @Override
    public RespBean addCard(CardVo cardVo) {

        //首先判断银行卡是否被绑定
        logger.info("绑定银行卡：" + cardVo.toString());
        Card card = null;
        try {
            card = cardMapper.selectByCardId(cardVo.getCard_id());
        } catch (Exception e) {
            logger.error("银行卡查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
        }
        if (card != null) {
            logger.error("该银行卡已绑定");
            return RespBean.error(RespBeanEnum.CARD_ALREADY_BIND);
        }

        //判断绑定的卡主是否存在
        User user = null;
        try {
            user = userService.OutFindById(cardVo.getUser_id());
        } catch (Exception e) {
            logger.error("用户查询失败");
            return RespBean.error(RespBeanEnum.USER_FIND_ERROR);
        }
        if (user == null) {
            logger.info("用户不存在");
            return RespBean.error(RespBeanEnum.USER_NOT_EXIST);
        }

        //判断完成，进行绑定
        try {
            Random rd = new Random();
            cardMapper.addCard(0, cardVo.getCard_id(), cardVo.getUser_id(), new BigDecimal(rd.nextInt(3000) + rd.nextDouble()));
        } catch (Exception e) {
            logger.error("绑定失败:" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_INSERT_FAIL);
        }
        logger.info("绑定成功");
        return RespBean.success();
    }

    @Override
    public RespBean deleteCard(String card_id, String user_id) {
        logger.info("解绑银行卡：" + card_id);
        Card card = cardMapper.selectByCardId(card_id);
        if (card == null) {
            logger.error("该银行卡不存在");
            return RespBean.error(RespBeanEnum.CARD_NOT_EXIST);
        }
        if (!card.getUser_id().equals(user_id)) {
            logger.error("该银行卡未与用户形成绑定关系，无法进行解绑操作");
            return RespBean.error(RespBeanEnum.CARD_NOT_BIND);
        }
        try {
            cardMapper.deleteCard(card_id);
        } catch (Exception e) {
            logger.error("解绑失败:" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_UNBIND_FAIL);
        }
        logger.info("解绑成功");
        return RespBean.success();
    }

    @Override
    public RespBean topUpCard(String card_id, String user_id, BigDecimal amount) {
        logger.info("银行卡充值：" + card_id);
        Card card = null;
        try {
            card = cardMapper.selectByCardId(card_id);
        } catch (Exception e) {
            logger.error("银行卡查询失败：" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
        }
        if (card == null) {
            logger.error("该银行卡不存在");
            return RespBean.error(RespBeanEnum.CARD_NOT_EXIST);
        }
        if (!card.getUser_id().equals(user_id)) {
            logger.error("您与该银行卡不存在绑定关系，无法进行充值");
            return RespBean.error(RespBeanEnum.CARD_CANT_TOPUP);
        }
        try {
            cardMapper.addCardAccount(amount, card_id);
        } catch (Exception e) {
            logger.error("充值失败:" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_TOPUP_FAIL);
        }
        logger.info("充值完成");
        return RespBean.success();
    }

    @Override
    public RespBean getAllCard(String user_id) {
        logger.info("所有银行卡查询");
        List<Card> cards = new ArrayList<>();
        try {
            cards = cardMapper.findAllCard(user_id);
        } catch (Exception e) {
            logger.error("查询失败:" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
        }
        logger.info("查询完成");
        return RespBean.success(cards);
    }

    @Override
    public RespBean finishBind(String user_id) {
        logger.info("结束绑定");
        List<Card> cards = new ArrayList<>();
        try {
            cards = cardMapper.findAllCard(user_id);
        } catch (Exception e) {
            logger.error("查询失败:" + e.getMessage());
            return RespBean.error(RespBeanEnum.CARD_FIND_FAIL);
        }
        if (cards == null) {
            logger.error("未绑定银行卡");
            return RespBean.error(RespBeanEnum.CARD_NUM_EMPTY);
        }
        logger.info("进入评估模块");
        return RespBean.success();
    }

}
