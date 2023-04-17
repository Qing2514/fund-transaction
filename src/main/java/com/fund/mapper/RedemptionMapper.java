package com.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fund.entity.Redemption;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface RedemptionMapper extends BaseMapper<Redemption> {

    @Select("select * from redemption where id = #{id} and state = #{state}")
    Redemption findById(@Param("id") String id, @Param("state") Integer state);

    @Select("select * from redemption where user_id = #{userId} and state = #{state}")
    List<Redemption> findByUserId(@Param("userId") String userId, @Param("state") Integer state);

    @Select("select * from redemption where card_id = #{cardId} and state = #{state}")
    List<Redemption> findByCardId(@Param("cardId") String cardId, @Param("state") Integer state);

    @Select("select * from redemption where datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, " +
            "INTERVAL 1 DAY) and state = #{state}")
    List<Redemption> findByDate(@Param("datetime") Date datetime, @Param("state") Integer state);

    @Insert("insert into redemption values(#{redemption.id},#{redemption.userId},#{redemption.userName}," +
            "#{redemption.productId},#{redemption.productName},#{redemption.cardId},#{redemption.datetime}," +
            "0,#{redemption.share},0)")
    boolean addRedemption(@Param("redemption") Redemption redemption);

    @Update("update redemption set amount = #{amount}, state = 1 where state = 0 " +
            "and datetime < #{datetime} and datetime >= DATE_SUB(#{datetime}, INTERVAL 1 DAY) " +
            "and product_id = #{productId}")
    boolean finishRedemption(@Param("productId") String productId, @Param("amount") BigDecimal amount,
                           @Param("datetime") Date datetime);

    @Update("update redemption set state = 2 where id = #{id} and state = 0")
    boolean cancelRedemption(@Param("id") String id);

}
