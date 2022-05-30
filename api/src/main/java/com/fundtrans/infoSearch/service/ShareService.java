package com.fundtrans.infoSearch.service;


import com.fundtrans.pojo.Share;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
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

    RespBean getSum();


    RespBean findByAll(TransSelectVo transSelectVo);

    RespBean getShare(String user_id, String product_id, String card_id);

    RespBean findByUserId(TransSelectVo transSelectVo);
}
