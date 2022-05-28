package com.fundtrans.infoSearch.client.controller;

import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    @CloudReference
    private RecordService recordService;

    @GetMapping("/getSum")
    public RespBean getSum(){
        return recordService.getSum();
    }

    /**
     * user_id,product_id,card_id,date所有份额流水记录查询接口
     * @param transSelectVo
     * @return
     */
    @PostMapping("/findByAll")
    public RespBean findByAll(@RequestBody TransSelectVo transSelectVo){
        return recordService.findByAll(transSelectVo);
    }
}
