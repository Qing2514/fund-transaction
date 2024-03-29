package com.fund.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Card;
import com.fund.vo.CardVo;

import java.math.BigDecimal;
import java.util.List;

public interface CardService extends IService<Card> {

    IPage<Card> findAll(int currentPage, int pageSize);

    List<Card> findByUserId(String userId);

    Card findByCardId(String cardId);

    List<Card> findCard(String cardId, String userName);

    boolean addCard(CardVo cardVo);

    boolean deleteCardByCardId(String cardId);

    boolean deleteCardByUserId(String userId);

    boolean recharge(String cardId, BigDecimal amount);

    boolean purchase(String cardId, BigDecimal amount);

}
