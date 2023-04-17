package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Redemption;
import com.fund.vo.RedemptionVo;

import java.util.Date;
import java.util.List;

public interface RedemptionService extends IService<Redemption> {

    Redemption findById(String id, Integer state);

    List<Redemption> findByUserId(String userId, Integer state);

    List<Redemption> findByCardId(String cardId, Integer state);

    List<Redemption> findByDate(Date date, Integer state);

    boolean addRedemption(RedemptionVo redemptionVo);

    boolean finishRedemption(Date date);

    boolean cancelRedemption(String id);

    boolean cancelRedemptionByUserId(String userId);

}
