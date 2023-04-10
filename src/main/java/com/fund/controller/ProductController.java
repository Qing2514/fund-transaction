package com.fund.controller;

import com.fund.entity.Product;
import com.fund.service.ProductService;
import com.fund.util.AjaxResult;
import com.fund.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "ProductController", tags = "产品模块")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("查询所有产品")
    @GetMapping("/findAll")
    public AjaxResult findAllProduct() {
        return productService.findAll();
    }

    @ApiOperation("根据id查询产品")
    @GetMapping("/findById/{id}")
    public AjaxResult findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @ApiOperation("根据id模糊查询产品")
    @GetMapping("/findByFuzzyId/{id}")
    public AjaxResult findByFuzzyId(@PathVariable("id") String id) {
        return productService.findByFuzzyId(id);
    }

    @ApiOperation("根据id模糊查询产品")
    @GetMapping("/findProduct/{type}/{name}/{security}")
    public AjaxResult findProduct(@PathVariable("type") Integer type, @PathVariable("name") String name,
                                  @PathVariable("security") Integer security) {
        return productService.findProduct(type, name, security);
    }

    @ApiOperation("新增产品")
    @PostMapping("/addProduct")
    public AjaxResult addProduct(@Valid @RequestBody ProductVo productVo) {
        return productService.addProduct(productVo);
    }

    @ApiOperation("更新产品")
    @PutMapping("/updateProduct")
    public AjaxResult updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @ApiOperation("删除产品")
    @DeleteMapping("/deleteProduct/{id}")
    public AjaxResult deleteProduct(@PathVariable("id") String id){
        return productService.deleteProduct(id);
    }

    @ApiOperation("获取产品总数")
    @GetMapping("/getSum")
    public AjaxResult getSum(){
        return productService.getSum();
    }

}
