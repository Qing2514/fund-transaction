package com.fundtrans.fundRedemption.client.controller;

import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/redemption")
@CrossOrigin
public class RedemptionController {

    @CloudReference
    private RedemptionService redemptionService;

    @GetMapping("/doRedemption")
    public RespBean doRedemption(Date date){
        return redemptionService.doRedemption(date);
    }
}
