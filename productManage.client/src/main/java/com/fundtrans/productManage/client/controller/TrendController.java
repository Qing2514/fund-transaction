package com.fundtrans.productManage.client.controller;

import com.fundtrans.pojo.Trend;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trend")
@CrossOrigin
public class TrendController {
    @CloudReference
    private TrendService trendService;

    //显示净值
    @GetMapping("/findTrendById/{product_id}")
    public RespBean findTrendById(@PathVariable("product_id") String product_id) {
        return trendService.findTrendById(product_id);
    }

    @PostMapping("/addTrend")
    public RespBean addTrend(@RequestBody Trend trend) {
        return trendService.addTrend(trend);
    }
}
