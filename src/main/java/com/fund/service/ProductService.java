package com.fund.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Product;
import com.fund.vo.ProductVo;

import java.util.List;

public interface ProductService extends IService<Product> {

    IPage<Product> findAll(int currentPage, int pageSize);

    Product findById(String id);

    List<Product> findProduct(String id, String name);

    boolean addProduct(ProductVo productVo);

    boolean updateProduct(Product product);

    boolean deleteProduct(String id);

    List<String> getIds();

}
