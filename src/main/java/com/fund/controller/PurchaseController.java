package com.fund.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fund.entity.Purchase;
import com.fund.service.PurchaseService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.PurchaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "PurchaseController", tags = "申购模块")
@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation("根据订单状态查询所有订单")
    @GetMapping("/findAll/{state}")
    public AjaxResult findAll(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize,
                              @PathVariable Integer state) {
        IPage<Purchase> page = purchaseService.findAll(currentPage, pageSize, state);
        return AjaxResult.success(page.getRecords());
    }

    @ApiOperation("根据订单号、客户名称、产品名称、银行卡号、申购日期、订单状态模糊查询")
    @GetMapping("/findPurchase/{state}")
    public AjaxResult findPurchase(@PathVariable Integer state, @RequestParam("id") String id,
                                   @RequestParam("userName") String userName, @RequestParam("productName") String productName,
                                   @RequestParam("cardId") String cardId,
                                   @RequestParam("date") String date) {
        return AjaxResult.success(purchaseService.findPurchase(state, id, userName, productName, cardId, date));
    }

    @ApiOperation("根据客户证件号和订单状态查询订单")
    @GetMapping("/findByUserId/{userId}/{state}")
    public AjaxResult findByUserId(@PathVariable String userId, @PathVariable Integer state) {
        return AjaxResult.success(purchaseService.findByUserId(userId, state));
    }

    @ApiOperation("根据日期和订单状态查询订单")
    @GetMapping("/findByDate/{date}/{state}")
    public AjaxResult findByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                 @PathVariable Integer state) {
        return AjaxResult.success(purchaseService.findByDate(date, state));
    }

    @ApiOperation("新增申购")
    @PostMapping("/addPurchase")
    public AjaxResult addPurchase(@RequestBody PurchaseVo purchaseVo) {
        return purchaseService.addPurchase(purchaseVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_NOT_EXIST_OR_NOT_BELONG_OR_LACK_AMOUNT);
    }

    @ApiOperation("完成申购")
    @PutMapping("/finishPurchase/{id}")
    public AjaxResult finishPurchase(@PathVariable String id) {
        return purchaseService.finishPurchase(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("根据日期批量完成申购")
    @PutMapping("/finishPurchaseByDate/{date}")
    public AjaxResult finishPurchaseByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return purchaseService.finishPurchaseByDate(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("取消申购")
    @DeleteMapping("/cancelPurchase/{id}")
    public AjaxResult cancelPurchase(@PathVariable String id) {
        return purchaseService.cancelPurchase(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PURCHASE_NOT_EXIST);
    }

    @ApiOperation("根据日期批量取消申购")
    @DeleteMapping("/cancelPurchaseByDate/{date}")
    public AjaxResult cancelPurchaseByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return purchaseService.cancelPurchaseByDate(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PURCHASE_NOT_EXIST);
    }

}
