package com.fundtrans.userManage.service;

import com.fundtrans.userManage.vo.CardVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.math.BigDecimal;

@CloudService
public interface CardService {

    RespBean addCard(CardVo cardVo);

    RespBean deleteCard(String card_id,String user_id);

    RespBean topUpCard(String card_id, String user_id, BigDecimal amount);

    RespBean getAllCard(String user_id);

    RespBean finishBind(String user_id);
}
