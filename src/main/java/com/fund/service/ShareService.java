package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Share;

import java.util.List;

public interface ShareService extends IService<Share> {

    List<Share> findByUserId(String userId);

    Share findByUserIdAndProductId(String userId, String productId);

    boolean addShare(Share share);

    boolean reduceShare(Share share);

}
