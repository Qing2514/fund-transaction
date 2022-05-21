package com.fundtrans.fundRedemption.client.controller;

import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redemption")
public class RedemptionController {

    @CloudReference
    private RedemptionService redemptionService;

    @GetMapping("/doRedemption")
    public RespBean doRedemption(){
        return redemptionService.doRedemption();
    }
}
