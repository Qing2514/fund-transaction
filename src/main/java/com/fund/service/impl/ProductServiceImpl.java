package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.mapper.ProductMapper;
import com.fund.service.ProductService;
import com.fund.service.TrendService;
import com.fund.util.ClearingUtil;
import com.fund.util.UUIDUtil;
import com.fund.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private TrendService trendService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Product findById(String id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findByFuzzyId(String id) {
        return productMapper.findByFuzzyId(id);
    }

    @Override
    public Product findProduct(Integer type, String name, Integer security) {
        return productMapper.findProduct(type, name, security);
    }

    @Override
    public boolean addProduct(ProductVo productVo) {
        // 判断产品是否已存在
        Product temp = productMapper.findByTypeAndName(productVo.getType(), productVo.getName());
        if (temp != null) {
            return false;
        }
        Product product = new Product();
        BeanUtils.copyProperties(productVo, product);
        product.setId(UUIDUtil.getUUID());
        product.setDate(ClearingUtil.getDate());
        // 添加产品走势
        trendService.addTrendByProductId(product.getId());
        return productMapper.addProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        Product temp = productMapper.findById(product.getId());
        if (temp == null) {
            return false;
        }
        return productMapper.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(String id) {
        Product temp = productMapper.findById(id);
        if (temp == null) {
            return false;
        }
        return productMapper.deleteProduct(id);
    }

    @Override
    public List<String> getIds() {
        return productMapper.getIds();
    }

    @Override
    public int getSum() {
        return productMapper.getSum();
    }

}
