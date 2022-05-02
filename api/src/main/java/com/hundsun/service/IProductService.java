package com.hundsun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.entity.Product;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudService
public interface IProductService extends IService<Product> {

}
