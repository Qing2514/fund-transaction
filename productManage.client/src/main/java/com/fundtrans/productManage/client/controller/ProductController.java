package com.fundtrans.productManage.client.controller;

import com.fundtrans.pojo.Product;
import com.fundtrans.pojo.Trend;
import com.fundtrans.productManage.service.ProductService;
import com.fundtrans.productManage.service.TrendService;
import com.fundtrans.vo.RespBean;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping("/addProduct/{date}")
    public RespBean addProduct(@RequestBody Product product,@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        return productService.addProduct(product,date);
    }

    @PutMapping("/updateProduct")
    public RespBean updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/findProductById/{id}")
    public RespBean findProductById(@PathVariable("id") String id) {
        return productService.findProductById(id);
    }


    @DeleteMapping("/deleteProduct/{id}")
    public RespBean deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id);
    }

    /**
     * 查询产品记录总数
     * @return
     */
    @GetMapping("/getSum")
    public RespBean getSum(){
        return productService.getSum();
    }

    /**
     * 通过product_id模糊查询
     * @param id
     * @return
     */
    @GetMapping("/findProductByIdBubble/{id}")
    public RespBean findProductByIdBubble(@PathVariable("id") String id) {
        return productService.findBubbleId(id);
    }
}
