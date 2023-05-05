package com.fund.controller;

import com.fund.service.CardService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.CardVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;

@Api(value = "CardController", tags = "银行卡模块")
@RestController
@RequestMapping("/card")
@CrossOrigin
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation("查询全部银行卡")
    @GetMapping("/findAll")
    public AjaxResult findAll() {
        return AjaxResult.success(cardService.findAll());
    }

    @ApiOperation("根据客户证件号查询银行卡")
    @GetMapping("/findByUserId/{userId}")
    public AjaxResult findByUserId(@PathVariable("userId") String userId) {
        return AjaxResult.success(cardService.findByUserId(userId));
    }

    @ApiOperation("根据银行卡号和客户名称查询银行卡")
    @GetMapping("/findCard")
    public AjaxResult findCard(@PathParam("cardId") String cardId, @PathParam("userName") String userName) {
        return AjaxResult.success(cardService.findCard(cardId, userName));
    }

    @ApiOperation("绑定银行卡")
    @PostMapping("/addCard")
    public AjaxResult addCard(@Valid @RequestBody CardVo cardVo) {
        return cardService.addCard(cardVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_EXIST_OR_USER_NOT_EXIST);
    }

    @ApiOperation("解绑银行卡")
    @DeleteMapping("/deleteCard/{cardId}")
    public AjaxResult deleteCard(@PathVariable("cardId") String cardId) {
        return cardService.deleteCardByCardId(cardId) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_NOT_EXIST_OR_HAVING_ORDERS);
    }

    @ApiOperation("银行卡金额充值")
    @PutMapping("/recharge/{cardId}/{amount}")
    public AjaxResult recharge(@PathVariable("cardId") String cardId, @PathVariable("amount") BigDecimal amount) {
        return cardService.recharge(cardId, amount) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_NOT_EXIST);
    }

}
