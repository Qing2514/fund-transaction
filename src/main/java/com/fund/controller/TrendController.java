package com.fund.controller;

import com.fund.service.TrendService;
import com.fund.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "TrendController", tags = "产品走势模块")
@RestController
@RequestMapping("/trend")
public class TrendController {

    @Autowired
    private TrendService trendService;

    @ApiOperation("根据产品id查询走势")
    @GetMapping("/findById/{productId}")
    public AjaxResult findById(@PathVariable("productId") String productId) {
        return AjaxResult.success(trendService.findById(productId));
    }

    @ApiOperation("新增产品走势")
    @PostMapping("/addTrend/{productId}")
    public AjaxResult addTrend(@PathVariable("productId") String productId) {
        return trendService.addTrend(productId) ? AjaxResult.success() : AjaxResult.error();
    }

}
