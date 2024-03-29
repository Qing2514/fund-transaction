package com.fund.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fund.entity.Product;
import com.fund.service.ProductService;
import com.fund.util.AjaxResult;
import com.fund.util.ResultEnum;
import com.fund.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "ProductController", tags = "产品模块")
@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation("查询所有产品")
    @GetMapping("/findAll")
    public AjaxResult findAll(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize) {
        IPage<Product> page = productService.findAll(currentPage, pageSize);
        return AjaxResult.success(page.getRecords());
    }

    @ApiOperation("根据产品id和产品名称模糊查询产品")
    @GetMapping("/findProduct")
    public AjaxResult findProduct(@RequestParam("id") String id, @RequestParam("name") String name) {
        return AjaxResult.success(productService.findProduct(id, name));
    }

    @ApiOperation("新增产品")
    @PostMapping("/addProduct")
    public AjaxResult addProduct(@Valid @RequestBody ProductVo productVo) {
        return productService.addProduct(productVo) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PRODUCT_ALREADY_EXIST);
    }

    @ApiOperation("更新产品")
    @PutMapping("/updateProduct")
    public AjaxResult updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product) ? AjaxResult.success() :
                AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
    }

    @ApiOperation("删除产品")
    @DeleteMapping("/deleteProduct/{id}")
    public AjaxResult deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id) ? AjaxResult.success() : AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
    }

}
