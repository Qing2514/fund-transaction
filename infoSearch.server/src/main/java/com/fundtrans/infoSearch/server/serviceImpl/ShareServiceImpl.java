package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.pojo.Share;
import com.fundtrans.infoSearch.server.mapper.ShareMapper;
import com.fundtrans.infoSearch.service.ShareService;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CloudComponent
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareMapper shareMapper;

    @Override
    public List<Share> OutJudgeCard(String userId, String productId) {
        return shareMapper.judgeCard(userId,productId);
    }

    @Override
    public Share OutFindByThree(String userId, String productId, String cardId) {
        return shareMapper.findByThree(userId, productId, cardId);
    }

    @Override
    public void OutUpdateCount(Share share) {
        shareMapper.updateCount(share);
    }

    @Override
    public void OutDeleteShare(Share share) {
        shareMapper.deleteShare(share);
    }

    @Override
    public void OutAddShare(Share share) {
        shareMapper.addShare(share);
    }

    @Override
    public void OutUpdateShareAdd(Share share) {
        shareMapper.updateShareAdd(share);
    }
}
