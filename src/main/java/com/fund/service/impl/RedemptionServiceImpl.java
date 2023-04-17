package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Card;
import com.fund.entity.Product;
import com.fund.entity.Redemption;
import com.fund.entity.User;
import com.fund.mapper.RedemptionMapper;
import com.fund.service.*;
import com.fund.util.ClearingUtil;
import com.fund.util.UUIDUtil;
import com.fund.vo.RedemptionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RedemptionServiceImpl extends ServiceImpl<RedemptionMapper, Redemption> implements RedemptionService {

    @Autowired
    private RedemptionMapper redemptionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CardService cardService;

    @Autowired
    private TrendService trendService;

    @Override
    public Redemption findById(String id, Integer state) {
        return redemptionMapper.findById(id, state);
    }

    @Override
    public List<Redemption> findByUserId(String userId, Integer state) {
        return redemptionMapper.findByUserId(userId, state);
    }

    @Override
    public List<Redemption> findByCardId(String cardId, Integer state) {
        return redemptionMapper.findByUserId(cardId, state);
    }

    @Override
    public List<Redemption> findByDate(Date date, Integer state) {
        Date datetime = ClearingUtil.setDate(date, 24);
        return redemptionMapper.findByDate(datetime, state);
    }

    @Override
    public boolean addRedemption(RedemptionVo redemptionVo) {
        User user = userService.findById(redemptionVo.getUserId());
        Product product = productService.findById(redemptionVo.getProductId());
        Card card = cardService.findByCardId(redemptionVo.getCardId());
        if(user == null || product == null || card == null) {
            return false;
        }
        // todo:减少份额表份额
        // 新增赎回
        Redemption redemption = new Redemption();
        BeanUtils.copyProperties(redemptionVo, redemption);
        redemption.setId(UUIDUtil.getUUID());
        redemption.setUserName(user.getName());
        redemption.setProductName(product.getName());
        redemption.setDatetime(new Date());
        return redemptionMapper.addRedemption(redemption);
    }

    @Override
    public boolean finishRedemption(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Redemption> rList = redemptionMapper.findByDate(newDate, 0);
        for(Redemption redemption : rList) {
            BigDecimal netWorth = trendService.getPrice(redemption.getProductId(), date);
            if(BigDecimal.ZERO.equals(netWorth)) {
                return false;
            }
            redemptionMapper.finishRedemption(redemption.getProductId(), netWorth, newDate);
        }
        return true;
    }

    @Override
    public boolean cancelRedemption(String id) {
        Redemption redemption = redemptionMapper.findById(id, 0);
        if(redemption == null) {
            return false;
        }
        // todo:份额表归还份额
        // 取消赎回
        return redemptionMapper.cancelRedemption(id);
    }

    @Override
    public boolean cancelRedemptionByUserId(String userId) {
        List<Redemption> redemptionList = redemptionMapper.findByUserId(userId, 0);
        for(Redemption redemption : redemptionList) {
            // todo:份额表归还份额
            redemptionMapper.cancelRedemption(redemption.getId());
        }
        return true;
    }

}
