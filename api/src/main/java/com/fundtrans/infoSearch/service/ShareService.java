package com.fundtrans.infoSearch.service;


import com.fundtrans.pojo.Share;
import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.util.List;

@CloudService
public interface ShareService {
    List<Share> OutJudgeCard(String userId, String productId);

    Share OutFindByThree(String userId, String productId, String cardId);

    void OutUpdateCount(Share share);

    void OutDeleteShare(Share share);

    void OutAddShare(Share share);

    void OutUpdateShareAdd(Share share);
}
