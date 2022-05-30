package com.fundtrans.productManage.client.controller;

import com.fundtrans.pojo.Trend;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/addTrend/{date}")
    public RespBean addTrend(@RequestBody Trend trend, @PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date date) {
        return trendService.addTrend(trend,date);
    }

    @GetMapping("/getSum")
    public RespBean getSum(){
        return trendService.getSum();
    }

    @PostMapping("/findByAll")
    public RespBean findByAll(@RequestBody TransSelectVo transSelectVo){
        return trendService.findByAll(transSelectVo);
    }
}
