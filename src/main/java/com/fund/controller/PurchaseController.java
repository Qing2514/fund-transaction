package com.fund.controller;

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
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation("根据订单id和订单状态查询订单")
    @GetMapping("/findById/{id}/{state}")
    public AjaxResult findById(@PathVariable String id, @PathVariable Integer state) {
        return AjaxResult.success(purchaseService.findById(id, state));
    }

    @ApiOperation("根据用户id和订单状态查询订单")
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
                AjaxResult.error(ResultEnum.CARD_OR_USER_OR_PRODUCT_NOT_EXIST);
    }

    @ApiOperation("完成申购")
    @PutMapping("/finishPurchase/{date}")
    public AjaxResult finishPurchase(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return purchaseService.finishPurchase(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("取消申购")
    @DeleteMapping("/cancelPurchase/{id}")
    public AjaxResult cancelPurchase(@PathVariable String id) {
        return purchaseService.cancelPurchase(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PURCHASE_NOT_EXIST);
    }

}
