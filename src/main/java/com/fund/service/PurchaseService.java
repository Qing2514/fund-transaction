package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Purchase;
import com.fund.vo.PurchaseVo;

import java.util.Date;
import java.util.List;

public interface PurchaseService extends IService<Purchase> {

    List<Purchase> findAll(Integer state);

    List<Purchase> findByUserId(String userId, Integer state);

    List<Purchase> findByCardId(String cardId, Integer state);

    List<Purchase> findByDate(Date date, Integer state);

    List<Purchase> findPurchase(Integer state, String id, String userName, String productName, String cardId,
                                String date);

    boolean addPurchase(PurchaseVo purchaseVo);

    boolean finishPurchase(String id);

    boolean finishPurchaseByDate(Date date);

    boolean cancelPurchase(String id);

    boolean cancelPurchaseByDate(Date date);

    boolean cancelPurchaseByUserId(String userId);

}
