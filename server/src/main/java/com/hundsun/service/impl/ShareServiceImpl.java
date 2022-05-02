package com.hundsun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hundsun.entity.Share;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import com.hundsun.mapper.ShareMapper;
import com.hundsun.service.IShareService;

/**
 * @author Qing2514
 * @date 2022-05-02
 */
@CloudComponent
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

}
