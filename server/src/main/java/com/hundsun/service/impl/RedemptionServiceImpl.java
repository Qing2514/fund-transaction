package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Redemption;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.RedemptionMapper;
import com.hundsun.service.IRedemptionService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class RedemptionServiceImpl extends ServiceImpl<RedemptionMapper, Redemption> implements IRedemptionService {

}
