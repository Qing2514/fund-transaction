package com.fund.controller;

import com.fund.service.TrendService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "TrendController", tags = "产品走势模块")
@RestController
@RequestMapping("/trend")
@CrossOrigin
public class TrendController {

    @Autowired
    private TrendService trendService;

    @ApiOperation("根据产品id查询走势")
    @GetMapping("/findById/{productId}")
    public AjaxResult findById(@PathVariable("productId") String productId) {
        return AjaxResult.success(trendService.findById(productId));
    }

    @ApiOperation("根据产品id新增产品走势")
    @PostMapping("/addTrend/{productId}")
    public AjaxResult addTrend(@PathVariable("productId") String productId) {
        return trendService.addTrendByProductId(productId) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
    }

    @ApiOperation("根据日期新增产品走势")
    @PostMapping("/addTrendByDate/{date}")
    public AjaxResult addTrendByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return trendService.addTrendByDate(date) ? AjaxResult.success() : AjaxResult.error(ResultEnum.DATE_ERROR);
    }

}
