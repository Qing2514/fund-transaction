package com.fundtrans.fundPurchase.client.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fundtrans.infoSearch.vo.CardVo;
import com.fundtrans.pojo.Ptrans;
import com.fundtrans.fundPurchase.service.PtransService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/ptrans")
@CrossOrigin
public class PtransController {
    @CloudReference
    private PtransService ptransService;

    /**
     * 添加申购交易记录
     * 通过输入传Ptrans,通过前端传时间
     * @param ptrans
     * @return
     */
    @PostMapping("/addPtrans")
    public RespBean addPtrans(@RequestBody Ptrans ptrans, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8") Date date) {
        return ptransService.addPtrans(ptrans, date);
    }

    /**
     * 在撤回申购阶段，I.首先根据用户ID查询其申购交易记录，并将其返回给前端，前端根据记录状态字段，将未处理的记录后面添加“撤回”按钮
     *
     * @param cardVo
     * @return
     */
    //-----------------------------------------------------------------------------
    @PostMapping("/ownPtrans")
    public RespBean ownPtrans(@RequestBody CardVo cardVo) {
        return ptransService.ownPtrans(cardVo.getUser_id());
    }

    /**
     * 撤回申购阶段 II.根据用户选择需要撤回的记录进行撤回
     * 通过前端传Ptrans和时间
     * @param ptrans
     * @return
     */
    @PostMapping("/withdrawPtrans")
    public RespBean withdrawPtrans(Ptrans ptrans, @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")Date date) {
        return ptransService.withdrawPtrans(ptrans, date);
    }






    /**
     * 查询总记录条数
     *
     * @return
     */
    @GetMapping("/getSum")
    public RespBean getSum() {
        return ptransService.getSum();
    }

    /**
     * 通过ID查询申购交易记录
     * @param id
     * @return
     */
    @PostMapping("/findById")
    public RespBean findById(int id){
        return ptransService.findById(id);
    }

    /**
     * user_id,product_id,card_id,date所有申购交易记录查询接口
     * @param transSelectVo
     * @return
     */
    @PostMapping("/findByAll")
    public RespBean findByAll(@RequestBody TransSelectVo transSelectVo) {
        return ptransService.findByAll(transSelectVo);
    }

}
