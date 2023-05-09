package com.fund.controller;

import com.fund.entity.Prediction;
import com.fund.service.PredictionService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "PredictionController", tags = "走势预测模块")
@RestController
@RequestMapping("/prediction")
@CrossOrigin
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @ApiOperation("根据产品代码查询")
    @GetMapping("/findByProductId/{productId}")
    public AjaxResult findByProductId(@PathVariable("productId") String productId) {
        return AjaxResult.success(predictionService.findByProductId(productId));
    }

    @ApiOperation("新增产品走势预测")
    @PostMapping("/addPrediction")
    public AjaxResult addPrediction(@RequestBody Prediction prediction) {
        return predictionService.addPrediction(prediction) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PREDICTION_ERROR);
    }

    @ApiOperation("根据产品代码删除")
    @PostMapping("/deletePrediction/{productId}")
    public AjaxResult deletePrediction(@PathVariable("productId") String productId) {
        return predictionService.deletePrediction(productId) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
    }

}
