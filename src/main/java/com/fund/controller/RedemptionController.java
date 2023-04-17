package com.fund.controller;

import com.fund.service.RedemptionService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.RedemptionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "RedemptionController", tags = "赎回模块")
@RestController
@RequestMapping("/redemption")
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @ApiOperation("根据订单id和订单状态查询订单")
    @GetMapping("/findById/{id}/{state}")
    public AjaxResult findById(@PathVariable String id, @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findById(id, state));
    }

    @ApiOperation("根据用户id和订单状态查询订单")
    @GetMapping("/findByUserId/{userId}/{state}")
    public AjaxResult findByUserId(@PathVariable String userId, @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findByUserId(userId, state));
    }

    @ApiOperation("根据日期和订单状态查询订单")
    @GetMapping("/findByDate/{date}/{state}")
    public AjaxResult findByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                 @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findByDate(date, state));
    }

    @ApiOperation("新增申购")
    @PostMapping("/addRedemption")
    public AjaxResult addRedemption(@RequestBody RedemptionVo purchaseVo) {
        return redemptionService.addRedemption(purchaseVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_OR_USER_OR_PRODUCT_NOT_EXIST_OR_LACK_SHARE);
    }

    @ApiOperation("完成申购")
    @PutMapping("/finishRedemption/{date}")
    public AjaxResult finishRedemption(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return redemptionService.finishRedemption(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("取消申购")
    @DeleteMapping("/cancelRedemption/{id}")
    public AjaxResult cancelRedemption(@PathVariable String id) {
        return redemptionService.cancelRedemption(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.REDEMPTION_NOT_EXIST);
    }

}
