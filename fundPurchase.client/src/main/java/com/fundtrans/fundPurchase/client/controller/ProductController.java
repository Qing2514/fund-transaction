package com.fundtrans.fundPurchase.client.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fundtrans.fundPurchase.service.ProductService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @CloudReference
    private ProductService productService;

    /**
     * 根据基金代码模糊查询
     * @param id
     * @return
     */
    //---------------------------------------------------------

    /**
     * 传入String序列化问题
     */
    @PostMapping("/SelectByProductId")
    public RespBean selectByProductId(String id) {
        return productService.selectByProductId(id + "%");
    }

    /**
     * 根据基金名称模糊查询
     * @param name
     * @return
     */
    //---------------------------------------------------------

    /**
     * 传入String序列化问题
     */
    @PostMapping("/SelectByProductName")
    public RespBean selectByProductName(String name) {
        return productService.selectByProductName(name + "%");
    }

    /**
     * 根据客户姓名模糊查询
     *
     * @param name
     * @return
     */
    @PostMapping("/SelectUserByName")
    public RespBean selectUserByName(String name) {
        return productService.selectUserByName(name + "%");
    }

}
