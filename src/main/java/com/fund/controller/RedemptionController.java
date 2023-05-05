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

import javax.websocket.server.PathParam;
import java.util.Date;

@Api(value = "RedemptionController", tags = "赎回模块")
@RestController
@RequestMapping("/redemption")
@CrossOrigin
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @ApiOperation("根据订单状态查询所有订单")
    @GetMapping("/findAll/{state}")
    public AjaxResult findAll(@PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findAll(state));
    }

    @ApiOperation("根据订单号、客户名称、产品名称、银行卡号、申购日期、订单状态模糊查询")
    @GetMapping("/findRedemption/{state}")
    public AjaxResult findRedemption(@PathVariable Integer state, @PathParam("id") String id,
                                     @PathParam("userName") String userName,
                                     @PathParam("productName") String productName, @PathParam("cardId") String cardId,
                                     @PathParam("date") String date) {
        return AjaxResult.success(redemptionService.findRedemption(state, id, userName, productName, cardId, date));
    }

    @ApiOperation("根据日期和订单状态查询订单")
    @GetMapping("/findByDate/{date}/{state}")
    public AjaxResult findByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                 @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findByDate(date, state));
    }

    @ApiOperation("新增赎回")
    @PostMapping("/addRedemption")
    public AjaxResult addRedemption(@RequestBody RedemptionVo redemptionVo) {
        return redemptionService.addRedemption(redemptionVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.CARD_OR_USER_OR_PRODUCT_NOT_EXIST_OR_LACK_SHARE);
    }

    @ApiOperation("完成赎回")
    @PutMapping("/finishRedemption/{id}")
    public AjaxResult finishRedemption(@PathVariable String id) {
        return redemptionService.finishRedemption(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("根据日期批量完成赎回")
    @PutMapping("/finishRedemptionByDate/{date}")
    public AjaxResult finishRedemptionByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return redemptionService.finishRedemptionByDate(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.NET_WORTH_NOT_EXIST);
    }

    @ApiOperation("取消赎回")
    @DeleteMapping("/cancelRedemption/{id}")
    public AjaxResult cancelRedemption(@PathVariable String id) {
        return redemptionService.cancelRedemption(id) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.REDEMPTION_NOT_EXIST);
    }

    @ApiOperation("根据日期批量取消赎回")
    @DeleteMapping("/cancelRedemptionByDate/{date}")
    public AjaxResult cancelRedemptionByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return redemptionService.cancelRedemptionByDate(date) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.REDEMPTION_NOT_EXIST);
    }

}
