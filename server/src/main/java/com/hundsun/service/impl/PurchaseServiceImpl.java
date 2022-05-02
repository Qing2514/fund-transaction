package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Purchase;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.PurchaseMapper;
import com.hundsun.service.IPurchaseService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements IPurchaseService {

}
