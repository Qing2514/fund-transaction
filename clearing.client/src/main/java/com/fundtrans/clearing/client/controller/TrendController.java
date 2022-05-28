package com.fundtrans.clearing.client.controller;

import com.fundtrans.clearing.service.ClearingService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/clearing")
@CrossOrigin
public class TrendController {

    @CloudReference
    private ClearingService clearingService;

    @GetMapping("/initializeDay/{date}/{step}")
    public RespBean initializeDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                  @PathVariable int step) {
        return clearingService.initializeDay(date, step);
    }

    @PostMapping("/updateNetWorth/{date}")
    public RespBean updateNetWorth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return clearingService.updateNetWorth(date);
    }

}
