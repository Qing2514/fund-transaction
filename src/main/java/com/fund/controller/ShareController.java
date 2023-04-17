package com.fund.controller;

import com.fund.service.ShareService;
import com.fund.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ShareController", tags = "份额模块")
@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @ApiOperation("根据客户id查询")
    @GetMapping("/findByUserId/{userId}")
    public AjaxResult findByUserId(@PathVariable String userId){
        return AjaxResult.success(shareService.findByUserId(userId));
    }

    @ApiOperation("根据客户id和产品id查询")
    @GetMapping("/findByUserIdAndProductId/{userId}/{productId}")
    public AjaxResult findByUserIdAndProductId(@PathVariable String userId, @PathVariable String productId){
        return AjaxResult.success(shareService.findByUserIdAndProductId(userId,productId));
    }

}
