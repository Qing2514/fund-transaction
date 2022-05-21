package com.fundtrans.fundPurchase.client.controller;

import com.fundtrans.fundPurchase.pojo.Ptrans;
import com.fundtrans.fundPurchase.service.PtransService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ptrans")
public class PtransController {
    @CloudReference
    private PtransService ptransService;

    /**
     * 添加申购交易记录
     * @param ptrans
     * @return
     */
    @PostMapping("/addPtrans")
    public RespBean addPtrans(@RequestBody Ptrans ptrans){
        return ptransService.addPtrans(ptrans);
    }

    /**
     * 在撤回申购阶段，I.首先根据用户ID查询其申购交易记录，并将其返回给前端，前端根据记录状态字段，将未处理的记录后面添加“撤回”按钮
     * @param user_id
     * @return
     */
    //-----------------------------------------------------------------------------
    @PostMapping("/ownPtrans")
    public RespBean ownPtrans(String user_id){
        return ptransService.ownPtrans(user_id);
    }

    /**
     * 撤回申购阶段 II.根据用户选择需要撤回的记录进行撤回
     * @param ptrans
     * @return
     */
    @PostMapping("/withdrawPtrans")
    public RespBean withdrawPtrans(Ptrans ptrans){
        return ptransService.withdrawPtrans(ptrans);
    }
}
