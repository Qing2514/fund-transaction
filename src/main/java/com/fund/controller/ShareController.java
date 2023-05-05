package com.fund.controller;

import com.fund.service.ShareService;
import com.fund.util.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Api(value = "ShareController", tags = "份额模块")
@RestController
@RequestMapping("/share")
@CrossOrigin
public class ShareController {

    @Autowired
    private ShareService shareService;

    @ApiOperation("查询所有")
    @GetMapping("/findAll")
    public AjaxResult findAll(){
        return AjaxResult.success(shareService.findAll());
    }

    @ApiOperation("根据客户id查询")
    @GetMapping("/findByUserId/{userId}")
    public AjaxResult findByUserId(@PathVariable String userId){
        return AjaxResult.success(shareService.findByUserId(userId));
    }

    @ApiOperation("根据客户id和产品id查询")
    @GetMapping("/findByUserIdAndProductId/{userId}/{productId}")
    public AjaxResult findByUserIdAndProductId(@PathVariable String userId, @PathVariable String productId){
        return AjaxResult.success(shareService.findByUserIdAndProductId(userId, productId));
    }

    @ApiOperation("根据客户名称和产品名称模糊查询")
    @GetMapping("/findShare")
    public AjaxResult findShare(@PathParam("userName") String userName, @PathParam("productName") String productName){
        return AjaxResult.success(shareService.findShare(userName, productName));
    }

}
