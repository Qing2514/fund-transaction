package com.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.mapper.ProductMapper;
import com.fund.service.ProductService;
import com.fund.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public IPage<Product> findAll(int currentPage, int pageSize) {
        IPage<Product> page = new Page<>(currentPage, pageSize);
        return productMapper.selectPage(page, null);
    }

    @Override
    public Product findById(String id) {
        return productMapper.findById(id);
    }

    @Override
    public List<Product> findProduct(String id, String name) {
        return productMapper.findProduct(id, name);
    }

    // @Override
    // public IPage<Product> findProduct(int currentPage, int pageSize, String id, String name) {
    //     IPage<Product> page = new Page<>(currentPage, pageSize);
    //     // 增加查询条件
    //     LambdaQueryWrapper<Product> lqw = new LambdaQueryWrapper<>();
    //     lqw.like(Product::getId, id).like(Product::getName, name).eq(Product::getState, 0);
    //     return productMapper.selectPage(page, lqw);
    // }

    @Override
    public boolean addProduct(ProductVo productVo) {
        // 判断产品是否已存在
        Product temp = productMapper.findById(productVo.getId());
        if (temp != null) {
            return false;
        }
        Product product = new Product();
        BeanUtils.copyProperties(productVo, product);
        product.setNetWorth(BigDecimal.ONE);
        product.setGrowth(BigDecimal.ZERO);
        product.setState(0);
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

}
