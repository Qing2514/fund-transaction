package com.fundtrans.infoSearch.client.controller;

import com.fundtrans.infoSearch.service.RecordService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
public class RecordController {

    @CloudReference
    private RecordService recordService;

    @GetMapping("/findByUserIdAndProductId/{userId}/{productId}")
    public RespBean findByUserIdAndProductId(@PathVariable String userId, @PathVariable String productId) {
        return recordService.findByUserIdAndProductId(userId, productId);
    }
}
