package com.fundtrans.clearing.client.controller;

import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/redemption")
public class RedemptionController {

    @CloudReference
    private RedemptionService redemptionService;

    @PostMapping("/updateRedemptionByDate/{date}/{productId}/{amount}")
    public RespBean updateRedemptionByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                         @PathVariable String productId, @PathVariable BigDecimal amount) {
        return redemptionService.updateRedemptionByDate(date, productId, amount);
    }
}
