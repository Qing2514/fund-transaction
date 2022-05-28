package com.fundtrans.infoSearch.service;

import com.fundtrans.pojo.Card;
import com.fundtrans.infoSearch.vo.CardVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CloudService
public interface CardService {

    RespBean findByUserId(String userId);

    RespBean addCard(CardVo cardVo);

    RespBean deleteCard(String card_id,String user_id);

    RespBean topUpCard(String card_id, String user_id, BigDecimal amount);

    RespBean getAllCard(String user_id);

    RespBean finishBind(String user_id);

    //对外开放模块调用

    List<Card> OutFindByUserId(String userId);

    void OutAddCard(int id, String card_id, String user_id, BigDecimal account);

    void OutDeleteCard(String card_id);

    Card OutSelectByCardId(String card_id);

    void OutAddCardAccount(BigDecimal amount,String card_id);

    List<Card> OutFindAllCard(String user_id);

    void OutUpdateCard(Card card);

    RespBean getSumByUserId(String user_id);

    RespBean getVacancy(String user_id, String card_id, Date date);
}
