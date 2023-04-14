package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Card;
import com.fund.entity.User;
import com.fund.mapper.CardMapper;
import com.fund.service.CardService;
import com.fund.service.UserService;
import com.fund.vo.CardVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

    @Autowired
    private UserService userService;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public List<Card> findByUserId(String userId) {
        return cardMapper.findByUserId(userId);
    }

    @Override
    public Card findByCardId(String cardId) {
        return cardMapper.findByCardId(cardId);
    }

    @Override
    public boolean addCard(CardVo cardVo) {
        // 判断银行卡是否被绑定及绑定的卡主是否存在
        Card card = cardMapper.findByCardId(cardVo.getCardId());
        User user = userService.findById(cardVo.getUserId());
        if (card != null || user == null) {
            return false;
        }
        // 进行绑定
        card = new Card();
        BeanUtils.copyProperties(cardVo, card);
        return cardMapper.addCard(card);
    }

    @Override
    public boolean deleteCardByCardId(String cardId) {
        Card card = cardMapper.findByCardId(cardId);
        if (card == null) {
            return false;
        }
        return cardMapper.deleteCardByCardId(cardId);
    }

    @Override
    public boolean deleteCardByUserId(String userId) {
        return cardMapper.deleteCardByUserId(userId);
    }

    @Override
    public boolean recharge(String cardId, BigDecimal account) {
        Card card = cardMapper.findByCardId(cardId);
        if (card == null) {
            return false;
        }
        return cardMapper.updateCard(cardId, card.getAccount().add(account));
    }

    @Override
    public boolean purchase(String cardId, BigDecimal account) {
        Card card = cardMapper.findByCardId(cardId);
        if (card == null) {
            return false;
        }
        return cardMapper.updateCard(cardId, card.getAccount().subtract(account));
    }

}
