package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Product;
import com.fund.util.AjaxResult;
import com.fund.vo.ProductVo;

public interface ProductService extends IService<Product> {

    AjaxResult findAll();

    AjaxResult findById(String id);

    AjaxResult findByFuzzyId(String id);

    AjaxResult findProduct(Integer type, String name, Integer security);

    AjaxResult addProduct(ProductVo productVo);

    AjaxResult updateProduct(Product product);

    AjaxResult deleteProduct(String id);

    AjaxResult getSum();
}
