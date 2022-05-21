package com.fundtrans.fundRedemption.client.controller;

import com.fundtrans.fundRedemption.pojo.Rtrans;
import com.fundtrans.fundRedemption.service.RtransService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rtrans")
public class RtransController {

    @CloudReference
    private RtransService rtransService;

    @PostMapping("/addRtrans")
    public RespBean addRtrans(@RequestBody Rtrans rtrans){
        return rtransService.addRtrans(rtrans);
    }

    //-----------------------------------------------------------
    //序列化和反序列化问题
    //通过map存储尝试
    @PostMapping("/ownRtrans")
    public RespBean ownRtrans(@RequestBody String user_id){
        return rtransService.ownRtrans(user_id);
    }

    @PostMapping("/withdrawRtrans")
    public RespBean withdrawRtrans(Rtrans rtrans){
        return rtransService.withdrawRtrans(rtrans);
    }
}
