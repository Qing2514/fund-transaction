package com.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fund.entity.*;
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

    @Autowired
    private ShareService shareService;

    @Override
    public IPage<Purchase> findAll(int currentPage, int pageSize, Integer state) {
        IPage<Purchase> page = new Page<>(currentPage, pageSize);
        // 增加查询条件
        LambdaQueryWrapper<Purchase> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Purchase::getState, state);
        return purchaseMapper.selectPage(page, lqw);
    }

    @Override
    public List<Purchase> findByUserId(String userId, Integer state) {
        return purchaseMapper.findByUserId(userId, state);
    }

    @Override
    public List<Purchase> findByCardId(String cardId, Integer state) {
        return purchaseMapper.findByCardId(cardId, state);
    }

    @Override
    public List<Purchase> findByDate(Date date, Integer state) {
        Date datetime = ClearingUtil.setDate(date, 15);
        return purchaseMapper.findByDate(datetime, state);
    }

    @Override
    public List<Purchase> findPurchase(Integer state, String id, String userName, String productName, String cardId,
                                       String date) {
        return purchaseMapper.findPurchase(state, id, userName, productName, cardId, date);
    }

    @Override
    public boolean addPurchase(PurchaseVo purchaseVo) {
        User user = userService.findByCid(purchaseVo.getUserId());
        Product product = productService.findById(purchaseVo.getProductId());
        Card card = cardService.findByCardId(purchaseVo.getCardId());
        if(user == null || product == null || card == null) {
            return false;
        }
        // 若银行卡不属于该客户，则无法申购
        if(!card.getUserId().equals(user.getCid())) {
            return false;
        }
        // 若银行卡余额不够，返回
        if(card.getAmount().compareTo(purchaseVo.getAmount()) == -1) {
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
    public boolean finishPurchase(String id) {
        Purchase purchase = purchaseMapper.findById(id, 0);
        if(purchase == null) {
            return false;
        }
        Date date = ClearingUtil.getDate(purchase.getDatetime());
        BigDecimal netWorth = trendService.getPrice(purchase.getProductId(), date);
        if(BigDecimal.ZERO.equals(netWorth)) {
            return false;
        }
        // 将获得份额写入份额表
        BigDecimal share = purchase.getAmount().divide(netWorth, 4);
        boolean flag = shareService.addShare(new Share(purchase.getUserId(), purchase.getUserName(),
                purchase.getProductId(), purchase.getProductName(), share));
        if(!flag) {
            return false;
        }
        // 完成订单
        return purchaseMapper.finishPurchase(purchase.getId(), share);
    }

    @Override
    public boolean finishPurchaseByDate(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Purchase> pList = purchaseMapper.findByDate(newDate, 0);
        for(Purchase purchase : pList) {
            BigDecimal netWorth = trendService.getPrice(purchase.getProductId(), date);
            if(BigDecimal.ZERO.equals(netWorth)) {
                return false;
            }
            // 将获得份额写入份额表
            BigDecimal share = purchase.getAmount().divide(netWorth, 4);
            boolean flag = shareService.addShare(new Share(purchase.getUserId(), purchase.getUserName(),
                    purchase.getProductId(), purchase.getProductName(), share));
            if(!flag) {
                return false;
            }
            // 完成订单
            purchaseMapper.finishPurchaseByDate(purchase.getProductId(), share, newDate);
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
    public boolean cancelPurchaseByDate(Date date) {
        Date newDate = ClearingUtil.setDate(date, 15);
        List<Purchase> pList = purchaseMapper.findByDate(newDate, 0);
        for(Purchase purchase : pList) {
            // 银行卡归还金额
            cardService.recharge(purchase.getCardId(), purchase.getAmount());
        }
        return purchaseMapper.cancelPurchaseByDate(ClearingUtil.getDate(newDate));
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
