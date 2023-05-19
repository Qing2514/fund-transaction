package com.fund.controller;

import com.fund.service.TrendService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.IncomeVo;
import com.fund.vo.PerformanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Api(value = "TrendController", tags = "产品走势模块")
@RestController
@RequestMapping("/trend")
@CrossOrigin
public class TrendController {

    @Autowired
    private TrendService trendService;

    @ApiOperation("根据产品代码查询走势")
    @GetMapping("/findById/{productId}")
    public AjaxResult findById(@PathVariable("productId") String productId) {
        return AjaxResult.success(trendService.findById(productId));
    }

    @ApiOperation("根据产品代码新增产品走势")
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

    @ApiOperation("根据天数查询一次性购入和定投的收益对比")
    @GetMapping("/compareIncomeByDays/{productId}/{days}/{amount}/{frequency}")
    public AjaxResult compareIncomeByDays(@PathVariable("productId") String productId,
                                          @PathVariable("days") Integer days,
                                          @PathVariable("amount") BigDecimal amount,
                                          @PathVariable("frequency") Integer frequency) {
        IncomeVo incomeVo = trendService.compareIncomeByDays(productId, days, amount, frequency);
        return incomeVo == null ? AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST_OR_FORMAT_ERROR) :
                AjaxResult.success(incomeVo);
    }

    @ApiOperation("根据日期查询一次性购入和定投的收益对比")
    @GetMapping("/compareIncomeByDate/{productId}/{startDate}/{endDate}/{amount}/{frequency}")
    public AjaxResult compareIncomeByDate(@PathVariable("productId") String productId,
                                    @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                    @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                                    @PathVariable("amount") BigDecimal amount,
                                    @PathVariable("frequency") Integer frequency) {
        IncomeVo incomeVo = trendService.compareIncomeByDate(productId, startDate, endDate, amount, frequency);
        return incomeVo == null ? AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST_OR_FORMAT_ERROR) :
                AjaxResult.success(incomeVo);
    }

    @ApiOperation("根据产品代码查询历史业绩")
    @GetMapping("/getPerformance/{productId}")
    public AjaxResult getPerformance(@PathVariable("productId") String productId) {
        List<PerformanceVo> performanceVo = trendService.getPerformance(productId);
        return performanceVo == null ? AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST) :
                AjaxResult.success(performanceVo);
    }

}
