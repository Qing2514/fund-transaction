package com.fundtrans.fundRedemption.client.controller;

import com.fundtrans.fundRedemption.service.RedemptionService;
import com.fundtrans.pojo.User;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/redemption")
@CrossOrigin
public class RedemptionController {

    @CloudReference
    private RedemptionService redemptionService;

    @GetMapping("/doRedemption/{date}")
    public RespBean doRedemption(@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date){
        return redemptionService.doRedemption(date);
    }

    /**
     * 查询赎回记录总条数
     * @return
     */
    @GetMapping("/getSum")
    public RespBean getSum(){
        return redemptionService.getSum();
    }

    /**
     * 通过赎回交易记录ID去查询
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public RespBean findById(int id){
        return redemptionService.findById(id);
    }
}
