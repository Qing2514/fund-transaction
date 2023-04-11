package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Product;
import com.fund.mapper.ProductMapper;
import com.fund.service.ProductService;
import com.fund.service.TrendService;
import com.fund.util.AjaxResult;
import com.fund.util.ClearingUtil;
import com.fund.util.ResultEnum;
import com.fund.util.UUIDUtil;
import com.fund.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private TrendService trendService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public AjaxResult findAll() {
        return AjaxResult.success(productMapper.findAll());
    }

    @Override
    public AjaxResult findById(String id) {
        return AjaxResult.success(productMapper.findById(id));
    }

    @Override
    public AjaxResult findByFuzzyId(String id) {
        return AjaxResult.success(productMapper.findByFuzzyId(id));
    }

    @Override
    public AjaxResult findProduct(Integer type, String name, Integer security) {
        return AjaxResult.success(productMapper.findProduct(type, name, security));
    }

    @Override
    public AjaxResult addProduct(ProductVo productVo) {
        // 判断产品是否已存在
        Product temp = productMapper.findByTypeAndName(productVo.getType(), productVo.getName());
        if (temp != null) {
            return AjaxResult.error(ResultEnum.PRODUCT_ALREADY_EXIST);
        }
        Product product = new Product();
        BeanUtils.copyProperties(productVo, product);
        product.setId(UUIDUtil.getUUID());
        product.setDate(ClearingUtil.getDate());
        productMapper.addProduct(product);
        // 添加产品走势
        trendService.addTrend(product.getId());
        return AjaxResult.success();
    }

    @Override
    public AjaxResult updateProduct(Product product) {
        Product temp = productMapper.findById(product.getId());
        if (temp == null) {
            return AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productMapper.updateProduct(product);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult deleteProduct(String id) {
        Product temp = productMapper.findById(id);
        if (temp == null) {
            return AjaxResult.error(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productMapper.deleteProduct(id);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult getSum() {
        return AjaxResult.success(productMapper.getSum());
    }

}
