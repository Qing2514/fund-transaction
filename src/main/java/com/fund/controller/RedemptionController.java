package com.fund.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fund.entity.Redemption;
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
@CrossOrigin
public class RedemptionController {

    @Autowired
    private RedemptionService redemptionService;

    @ApiOperation("根据订单状态查询所有订单")
    @GetMapping("/findAll/{state}")
    public AjaxResult findAll(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize,
                              @PathVariable Integer state) {
        IPage<Redemption> page = redemptionService.findAll(currentPage, pageSize, state);
        return AjaxResult.success(page.getRecords());
    }

    @ApiOperation("根据订单号、客户名称、产品名称、银行卡号、申购日期、订单状态模糊查询")
    @GetMapping("/findRedemption/{state}")
    public AjaxResult findRedemption(@PathVariable Integer state, @RequestParam("id") String id,
                                     @RequestParam("userName") String userName,
                                     @RequestParam("productName") String productName, @RequestParam("cardId") String cardId,
                                     @RequestParam("date") String date) {
        return AjaxResult.success(redemptionService.findRedemption(state, id, userName, productName, cardId, date));
    }

    @ApiOperation("根据日期和订单状态查询订单")
    @GetMapping("/findByDate/{date}/{state}")
    public AjaxResult findByDate(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                 @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findByDate(date, state));
    }

    @ApiOperation("根据客户证件号和订单状态查询订单")
    @GetMapping("/findByUserId/{userId}/{state}")
    public AjaxResult findByUserId(@PathVariable String userId, @PathVariable Integer state) {
        return AjaxResult.success(redemptionService.findByUserId(userId, state));
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
