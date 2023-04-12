package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Product;
import com.fund.vo.ProductVo;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<Product> findAll();

    Product findById(String id);

    List<Product> findByFuzzyId(String id);

    Product findProduct(Integer type, String name, Integer security);

    boolean addProduct(ProductVo productVo);

    boolean updateProduct(Product product);

    boolean deleteProduct(String id);

    int getSum();

}
