package com.hundsun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.entity.Purchase;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudService
public interface IPurchaseService extends IService<Purchase> {

}
