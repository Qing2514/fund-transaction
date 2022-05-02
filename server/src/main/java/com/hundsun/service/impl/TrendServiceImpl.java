package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Trend;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.TrendMapper;
import com.hundsun.service.ITrendService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class TrendServiceImpl extends ServiceImpl<TrendMapper, Trend> implements ITrendService {

}
