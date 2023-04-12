package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Card;
import com.fund.vo.CardVo;

import java.math.BigDecimal;
import java.util.List;

public interface CardService extends IService<Card> {

    List<Card> findByUserId(String userId);

    boolean addCard(CardVo cardVo);

    boolean deleteCard(String cardId);

    boolean recharge(String cardId, BigDecimal amount);

}