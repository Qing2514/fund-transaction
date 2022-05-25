package com.fundtrans.fundPurchase.client.controller;

import com.fundtrans.pojo.Ptrans;
import com.fundtrans.pojo.Purchase;
import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class PurchaseController {

    @CloudReference
    private PurchaseService purchaseService;

    @RequestMapping("/doPurchase")
    public RespBean doPurchase(Date date){
        return purchaseService.doPurchase(date);
    }
}
