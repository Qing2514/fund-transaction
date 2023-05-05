package com.fund.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fund.entity.Redemption;
import com.fund.vo.RedemptionVo;

import java.util.Date;
import java.util.List;

public interface RedemptionService extends IService<Redemption> {

    List<Redemption> findAll(Integer state);

    List<Redemption> findByUserId(String userId, Integer state);

    List<Redemption> findByCardId(String cardId, Integer state);

    List<Redemption> findByDate(Date date, Integer state);

    List<Redemption> findRedemption(Integer state, String id, String userName, String productName, String cardId,
                                    String date);

    boolean addRedemption(RedemptionVo redemptionVo);

    boolean finishRedemption(String id);

    boolean finishRedemptionByDate(Date date);

    boolean cancelRedemption(String id);

    boolean cancelRedemptionByDate(Date date);

    boolean cancelRedemptionByUserId(String userId);

}
