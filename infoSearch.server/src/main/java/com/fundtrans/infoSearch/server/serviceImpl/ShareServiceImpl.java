package com.fundtrans.infoSearch.server.serviceImpl;

import com.fundtrans.pojo.Share;
import com.fundtrans.infoSearch.server.mapper.ShareMapper;
import com.fundtrans.infoSearch.service.ShareService;
import com.fundtrans.vo.RespBean;
import com.fundtrans.vo.TransSelectVo;
import com.hundsun.jrescloud.rpc.annotation.CloudComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
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
        return RespBean.success(share.getValue().subtract(share.getFrozen_num()));
    }

    @Override
    public RespBean findShareByUserId(TransSelectVo transSelectVo) {
        List<Share> result = new ArrayList<>();
        List<String> product_ids = shareMapper.findProductIdByUserId(transSelectVo.getUser_id());
        for (int i = 0; i < product_ids.size(); i++){
            BigDecimal num_sum = new BigDecimal(0.00);
            Share share = new Share();
            List<Share> shares = shareMapper.findByUserIdAndProductId(transSelectVo.getUser_id(),product_ids.get(i));
            int j = 0;
            for (;j<shares.size();++j){
                num_sum = num_sum.add(shares.get(j).getValue());
            }
            share.setProduct_id(product_ids.get(i));
            share.setName(shares.get(j-1).getName());
            share.setValue(num_sum);
            result.add(share);
        }
        return RespBean.success(result);
    }
}
