package com.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.*;
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

    @Autowired
    private ShareService shareService;

    @Override
    public IPage<Redemption> findAll(int currentPage, int pageSize, Integer state) {
        IPage<Redemption> page = new Page<>(currentPage, pageSize);
        // 增加查询条件
        LambdaQueryWrapper<Redemption> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Redemption::getState, state);
        return redemptionMapper.selectPage(page, lqw);
    }

    @Override
    public List<Redemption> findByUserId(String userId, Integer state) {
        return redemptionMapper.findByUserId(userId, state);
    }

    @Override
    public List<Redemption> findByCardId(String cardId, Integer state) {
        return redemptionMapper.findByCardId(cardId, state);
    }

    @Override
    public List<Redemption> findByDate(Date date, Integer state) {
        Date datetime = ClearingUtil.setDate(date, 15);
        return redemptionMapper.findByDate(datetime, state);
    }

    @Override
    public List<Redemption> findRedemption(Integer state, String id, String userName, String productName, String cardId,
                                           String date) {
        return redemptionMapper.findRedemption(state, id, userName, productName, cardId, date);
    }

    @Override
    public boolean addRedemption(RedemptionVo redemptionVo) {
        User user = userService.findByCid(redemptionVo.getUserId());
        Product product = productService.findById(redemptionVo.getProductId());
        Card card = cardService.findByCardId(redemptionVo.getCardId());
        if(user == null || product == null || card == null) {
            return false;
        }
        // 减少份额表份额
        boolean flag = shareService.reduceShare(new Share(redemptionVo.getUserId(), user.getName(),
                redemptionVo.getProductId(), product.getName(), redemptionVo.getShare()));
        if(!flag) {
            return false;
        }
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
    public boolean finishRedemption(String id) {
        Redemption redemption = redemptionMapper.findById(id, 0);
        if(redemption == null) {
            return false;
        }
        Date date = ClearingUtil.getDate(redemption.getDatetime());
        BigDecimal netWorth = trendService.getPrice(redemption.getProductId(), date);
        if(BigDecimal.ZERO.equals(netWorth)) {
            return false;
        }
        // 将获得金额写入银行卡
        BigDecimal amount = redemption.getShare().multiply(netWorth);
        cardService.recharge(redemption.getCardId(), amount);
        // 完成订单
        return redemptionMapper.finishRedemption(redemption.getId(), amount);
    }

    @Override
    public boolean finishRedemptionByDate(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Redemption> rList = redemptionMapper.findByDate(newDate, 0);
        for(Redemption redemption : rList) {
            BigDecimal netWorth = trendService.getPrice(redemption.getProductId(), date);
            if(BigDecimal.ZERO.equals(netWorth)) {
                return false;
            }
            // 将获得金额写入银行卡
            BigDecimal amount = redemption.getShare().multiply(netWorth);
            cardService.recharge(redemption.getCardId(), amount);
            // 完成订单
            redemptionMapper.finishRedemptionByDate(redemption.getProductId(), amount, newDate);
        }
        return true;
    }

    @Override
    public boolean cancelRedemption(String id) {
        Redemption redemption = redemptionMapper.findById(id, 0);
        if(redemption == null) {
            return false;
        }
        // 份额表归还份额
        shareService.addShare(new Share(redemption.getUserId(), redemption.getUserName(),
                redemption.getProductId(), redemption.getProductName(), redemption.getShare()));
        // 取消赎回
        return redemptionMapper.cancelRedemption(id);
    }

    @Override
    public boolean cancelRedemptionByDate(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Redemption> rList = redemptionMapper.findByDate(newDate, 0);
        for(Redemption redemption : rList) {
            // 份额表归还份额
            shareService.addShare(new Share(redemption.getUserId(), redemption.getUserName(),
                    redemption.getProductId(), redemption.getProductName(), redemption.getShare()));
        }
        return redemptionMapper.cancelRedemptionByDate(ClearingUtil.getDate(newDate));
    }

    @Override
    public boolean cancelRedemptionByUserId(String userId) {
        List<Redemption> redemptionList = redemptionMapper.findByUserId(userId, 0);
        for(Redemption redemption : redemptionList) {
            // 份额表归还份额
            shareService.addShare(new Share(redemption.getUserId(), redemption.getUserName(),
                    redemption.getProductId(), redemption.getProductName(), redemption.getShare()));
            // 取消赎回
            redemptionMapper.cancelRedemption(redemption.getId());
        }
        return true;
    }

}
