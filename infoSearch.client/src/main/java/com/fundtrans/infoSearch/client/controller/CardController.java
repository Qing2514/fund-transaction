package com.fundtrans.infoSearch.client.controller;

import com.fundtrans.infoSearch.service.CardService;
import com.fundtrans.infoSearch.vo.CardVo;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/card")
@CrossOrigin
public class CardController {

    @CloudReference
    private CardService cardService;

    @GetMapping("/findByUserId")
    public RespBean findByUserId(@RequestParam("user_id") String user_id) {
        return cardService.findByUserId(user_id);
    }

    @GetMapping("/getAllCard")
    public RespBean getAllCard(@RequestParam("user_id") String user_id){
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
    public RespBean unbind(@RequestParam("card_id") String card_id,@RequestParam("user_id") String user_id){
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
    public RespBean topUp(@RequestBody CardVo cardVo){
        return cardService.topUpCard(cardVo.getCard_id(), cardVo.getUser_id(), cardVo.getAmount());
    }

//    /**
//     * 结束用户绑定
//     * @param user_id
//     * @return
//     */
//    @GetMapping("/finishBind")
//    public RespBean finishBind(String user_id){
//        return cardService.finishBind(user_id);
//    }
    @GetMapping("/getSum")
    public RespBean getSum(String user_id){
        return cardService.getSumByUserId(user_id);
    }

    /**
     * 余额查询
     * @param user_id
     * @param card_id
     * @param date
     * @return
     */
    @PostMapping("/getVacancy")
    public RespBean getVacancy(String user_id, String card_id,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
        return cardService.getVacancy(user_id,card_id,date);
    }
}
