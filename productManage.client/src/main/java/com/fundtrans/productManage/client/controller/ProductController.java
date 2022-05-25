package com.fundtrans.productManage.client.controller;

import com.fundtrans.pojo.Product;
import com.fundtrans.pojo.Trend;
import com.fundtrans.productManage.service.ProductService;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @CloudReference
    private ProductService productService;


    //显示所有产品信息
    @GetMapping("/findAllProduct/{index}/{limit}")
    public RespBean findAllProduct(@PathVariable("index") int index, @PathVariable("limit") int limit) {
        return productService.findAllProduct(index, limit);
    }

    @PostMapping("/addProduct")
    public RespBean addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct")
    public RespBean updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/findProductById/{id}")
    public RespBean findProductById(@PathVariable("id") String id) {
        return productService.findProductById(id);
    }

    @GetMapping("/findProductByName/{name}")
    public RespBean findProductByName(@PathVariable("name") String name) {
        return productService.findProductByName("%" + name + "%");
    }

    @GetMapping("/findProductByType/{type}")
    public RespBean findProductByType(@PathVariable("type") String productType) {
        return productService.findProductByType(productType);
    }

    @GetMapping("/findProductBySecurity/{security}")
    public RespBean findProductBySecurity(@PathVariable("security") String security) {
        return productService.findProductBySecurity(security);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public RespBean deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id);
    }
}
