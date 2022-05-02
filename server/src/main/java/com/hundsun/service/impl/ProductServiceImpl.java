package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Product;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.ProductMapper;
import com.hundsun.service.IProductService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
