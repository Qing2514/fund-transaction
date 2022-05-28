package com.fundtrans.fundPurchase.client.controller;

import com.fundtrans.fundPurchase.service.PurchaseService;
import com.fundtrans.pojo.User;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/purchase")
@CrossOrigin
public class PurchaseController {

    @CloudReference
    private PurchaseService purchaseService;

    @RequestMapping("/doPurchase")
    public RespBean doPurchase(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date){
        return purchaseService.doPurchase(date);
    }

    /**
     * 查询记录条数
     * @return
     */
    @GetMapping("/getSum")
    public RespBean getSum(){
        return purchaseService.getSum();
    }

    /**
     * 通过ID查询申购记录
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public RespBean findById(int id){
        return purchaseService.findById(id);
    }
}
