package com.hundsun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hundsun.entity.Redemption;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudService
public interface IRedemptionService extends IService<Redemption> {

}
