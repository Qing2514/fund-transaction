package com.fundtrans.infoSearch.client.controller;

import com.fundtrans.infoSearch.service.ShareService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
@CrossOrigin
public class ShareController {

    @CloudReference
    private ShareService shareService;

    @PostMapping("/getSum")
    public RespBean getSum(){
        return shareService.getSum();
    }

    /**
     * 所有份额查询接口
     * @param transSelectVo
     * @return
     */
    @GetMapping("/findByAll")
    public RespBean findByAll(TransSelectVo transSelectVo){
        return shareService.findByAll(transSelectVo);
    }

    /**
     * 份额查询接口
     * @param user_id
     * @param product_id
     * @param card_id
     * @return
     */
    @PostMapping("/getShare")
    public RespBean getShare(String user_id, String product_id, String card_id){
        return shareService.getShare(user_id,product_id,card_id);
    }

    @PostMapping("/findProductIdByUserId")
    public RespBean findProductIdByUserId(@RequestBody TransSelectVo transSelectVo){
        return shareService.findShareByUserId(transSelectVo);
    }
}
