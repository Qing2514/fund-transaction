package com.fundtrans.userManage.client.controller;

import com.fundtrans.userManage.service.CardService;
import com.fundtrans.userManage.vo.CardVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.def.json.SerializeBigDecimalDigit;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/card")
public class CardController {
    @CloudReference
    private CardService cardService;

    @GetMapping("/getAllCard")
    public RespBean getAllCard(String user_id){
        return cardService.getAllCard(user_id);
    }

    /**
     * 绑定银行卡
     * @param cardVo
     * @return
     */
    @PostMapping("/bind")
    public RespBean bind(@Valid @RequestBody CardVo cardVo){
        return cardService.addCard(cardVo);
    }

    /**
     * 银行卡解绑
     * @param card_id
     * @return
     */
    @DeleteMapping("/unbind")
    public RespBean unbind(String card_id,String user_id){
        return cardService.deleteCard(card_id,user_id);
    }

    /**
     * 银行卡充值
     * @param card_id
     * @param user_id
     * @param amount
     * @return
     */
    /**
     *存在大数以json格式传入 反序列化问题
     */
    @PutMapping("/topUp")
    public RespBean topUp(String card_id,String user_id, BigDecimal amount){
        return cardService.topUpCard(card_id,user_id,amount);
    }

    /**
     * 结束用户绑定
     * @param user_id
     * @return
     */
    @GetMapping("/finishBind")
    public RespBean finishBind(String user_id){
        return cardService.finishBind(user_id);
    }
}
