package com.fundtrans.clearing.client.controller;

import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class PurchaseController {

    @CloudReference
    private PurchaseService purchaseService;

    @PostMapping("/updatePurchaseByDate/{date}/{productId}/{count}")
    public RespBean updatePurchaseByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                  @PathVariable String productId, @PathVariable BigDecimal count) {
        return purchaseService.updatePurchaseByDate(date, productId, count);
    }
}
