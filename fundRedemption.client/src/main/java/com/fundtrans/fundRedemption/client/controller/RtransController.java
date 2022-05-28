package com.fundtrans.fundRedemption.client.controller;

import com.fundtrans.pojo.Rtrans;
import com.fundtrans.fundRedemption.service.RtransService;
import com.fundtrans.pojo.User;
import com.fundtrans.vo.Datetime;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/rtrans")
@CrossOrigin
public class RtransController {

    @CloudReference
    private RtransService rtransService;

    @PostMapping("/addRtrans")
    public RespBean addRtrans(@RequestBody Rtrans rtrans, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date){
        return rtransService.addRtrans(rtrans,date);
    }

    @PostMapping("/ownRtrans")
    public RespBean ownRtrans(@RequestBody User user){
        return rtransService.ownRtrans(user.getId());
    }

    @PostMapping("/withdrawRtrans")
    public RespBean withdrawRtrans(Rtrans rtrans,@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date){
        return rtransService.withdrawRtrans(rtrans,date);
    }


    /**
     * 赎回交易记录条数查询
     * @return
     */
    @GetMapping("/getSum")
    public RespBean getSum(){
        return rtransService.getSum();
    }

    /**
     * user_id,product_id,card_id,date所有赎回交易记录查询接口
     * @param transSelectVo
     * @return
     */
    @PostMapping("/findByAll")
    public RespBean findByAll(@RequestBody TransSelectVo transSelectVo){
        return rtransService.findByAll(transSelectVo);
    }

    /**
     * 通过赎回交易记录ID去查询
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public RespBean findById(int id){
        return rtransService.findById(id);
    }
}
