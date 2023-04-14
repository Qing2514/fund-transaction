package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Purchase;
import com.fund.vo.PurchaseVo;

import java.util.Date;
import java.util.List;

public interface PurchaseService extends IService<Purchase> {

    Purchase findById(String id, Integer state);

    List<Purchase> findByUserId(String userId, Integer state);

    List<Purchase> findByDate(Date date, Integer state);

    boolean addPurchase(PurchaseVo purchaseVo);

    boolean finishPurchase(Date date);

    boolean cancelPurchase(String id);

}
