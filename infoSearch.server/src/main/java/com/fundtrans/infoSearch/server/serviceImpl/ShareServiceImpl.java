package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.pojo.Share;
import com.fundtrans.infoSearch.server.mapper.ShareMapper;
import com.fundtrans.infoSearch.service.ShareService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
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

    @Override
    public RespBean getSum() {
        return RespBean.success(shareMapper.getSum());
    }

    @Override
    public RespBean findByAll(TransSelectVo transSelectVo) {
        return RespBean.success(shareMapper.findByAll(transSelectVo));
    }

    @Override
    public RespBean getShare(String user_id, String product_id, String card_id) {
        Share share = shareMapper.findByThree(user_id,product_id,card_id);
        return RespBean.success(share.getNum().subtract(share.getFrozen_num()));
    }
}
