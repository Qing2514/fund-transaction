package com.fundtrans.fundPurchase.client.controller;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Purchase;
import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @CloudReference
    private PurchaseService purchaseService;

    @RequestMapping("/doPurchase")
    public RespBean doPurchase(Date date){
        return purchaseService.doPurchase(date);
    }
}
