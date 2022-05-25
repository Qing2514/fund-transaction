package com.fundtrans.infoSearch.client.controller;

import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    @CloudReference
    private RecordService recordService;

    @GetMapping("/findByUserIdAndProductId/{userId}/{productId}")
    public RespBean findByUserIdAndProductId(@PathVariable String userId, @PathVariable String productId) {
        return recordService.findByUserIdAndProductId(userId, productId);
    }
}
