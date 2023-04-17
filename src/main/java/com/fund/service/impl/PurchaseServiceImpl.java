package com.fund.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.Card;
import com.fund.entity.Product;
import com.fund.entity.Purchase;
import com.fund.entity.User;
import com.fund.mapper.PurchaseMapper;
import com.fund.service.*;
import com.fund.util.ClearingUtil;
import com.fund.util.UUIDUtil;
import com.fund.vo.PurchaseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper, Purchase> implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CardService cardService;

    @Autowired
    private TrendService trendService;

    @Override
    public Purchase findById(String id, Integer state) {
        return purchaseMapper.findById(id, state);
    }

    @Override
    public List<Purchase> findByUserId(String userId, Integer state) {
        return purchaseMapper.findByUserId(userId, state);
    }

    @Override
    public List<Purchase> findByCardId(String cardId, Integer state) {
        return purchaseMapper.findByUserId(cardId, state);
    }

    @Override
    public List<Purchase> findByDate(Date date, Integer state) {
        Date datetime = ClearingUtil.setDate(date, 24);
        return purchaseMapper.findByDate(datetime, state);
    }

    @Override
    public boolean addPurchase(PurchaseVo purchaseVo) {
        User user = userService.findById(purchaseVo.getUserId());
        Product product = productService.findById(purchaseVo.getProductId());
        Card card = cardService.findByCardId(purchaseVo.getCardId());
        if(user == null || product == null || card == null) {
            return false;
        }
        // 若银行卡余额不够，返回
        if(card.getAccount().compareTo(purchaseVo.getAmount()) == -1) {
            return false;
        }
        // 银行卡余额足够，扣除金额
        cardService.purchase(purchaseVo.getCardId(), purchaseVo.getAmount());
        // 新增申购
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(purchaseVo, purchase);
        purchase.setId(UUIDUtil.getUUID());
        purchase.setUserName(user.getName());
        purchase.setProductName(product.getName());
        purchase.setDatetime(new Date());
        return purchaseMapper.addPurchase(purchase);
    }

    @Override
    public boolean finishPurchase(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Purchase> pList = purchaseMapper.findByDate(newDate, 0);
        for(Purchase purchase : pList) {
            BigDecimal netWorth = trendService.getPrice(purchase.getProductId(), date);
            if(BigDecimal.ZERO.equals(netWorth)) {
                return false;
            }
            purchaseMapper.finishPurchase(purchase.getProductId(), netWorth, newDate);
            // todo:份额写入份额表
        }
        return true;
    }

    @Override
    public boolean cancelPurchase(String id) {
        Purchase purchase = purchaseMapper.findById(id, 0);
        if(purchase == null) {
            return false;
        }
        // 银行卡归还金额
        cardService.recharge(purchase.getCardId(), purchase.getAmount());
        // 取消申购
        return purchaseMapper.cancelPurchase(id);
    }

    @Override
    public boolean cancelPurchaseByUserId(String userId) {
        List<Purchase> purchaseList = purchaseMapper.findByUserId(userId, 0);
        for(Purchase purchase : purchaseList) {
            // 银行卡归还金额
            cardService.recharge(purchase.getCardId(), purchase.getAmount());
            purchaseMapper.cancelPurchase(purchase.getId());
        }
        return true;
    }

}
